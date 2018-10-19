/*
 * Copyright 2016 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.github.raonigabriel;

import org.slf4j.*;
import com.github.benmanes.caffeine.cache.*;
import java.util.concurrent.TimeUnit;
import static spark.Spark.*;

public class SparkQrCodeApp {

	private static final Logger LOG = LoggerFactory.getLogger(SparkQrCodeApp.class);
	
	private static Cache<String, byte[]> cache;
	
	public static void main(String[] args) {
		port(8080);
		threadPool(8, 2, 30000);
		staticFiles.location("/static");
		staticFiles.expireTime(3600);
		cache = Caffeine.newBuilder().maximumSize(500).expireAfterAccess(3600, TimeUnit.SECONDS).build();
		
		get("/qrcode", (req, res) -> {
			final String textParam = req.queryParams("text");
			if (textParam != null && !textParam.isEmpty()) {
				byte[] png = cache.get(textParam, (text) -> ImageService.generateQRCode(text, 256, 256));
				res.type("image/png");
				res.header("Cache-Control", "max-age=3600");
				return png;
			} else {
				halt(400, "Query param 'text' is required");
				return null;
			}
		});
		
		exception(Exception.class, (ex, req, res) -> {
			LOG.error("Oops. Something went wrong.", ex);
			halt(500, "Internal Server Error");
		});
		
		awaitInitialization();
		LOG.info("Live and kicking!!");
	}
}