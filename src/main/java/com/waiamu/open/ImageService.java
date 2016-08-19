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
package com.waiamu.open;

import org.slf4j.*;
import com.google.zxing.*;
import com.google.zxing.client.j2se.*;
import com.google.zxing.common.BitMatrix;
import java.io.*;

public class ImageService {

	private static final Logger LOG = LoggerFactory.getLogger(SparkQrCodeApp.class);
	
	public static byte[] generateQRCode(String text, int width, int height) {

		LOG.info("Will generate image  text=[{}], width=[{}], height=[{}]", text, width, height);

		ByteArrayOutputStream baos = new ByteArrayOutputStream(); 
		BitMatrix matrix;
		try {
			matrix = new MultiFormatWriter().encode(text, BarcodeFormat.QR_CODE, width, height);
			MatrixToImageWriter.writeToStream(matrix, "png", baos, new MatrixToImageConfig());
			return baos.toByteArray();
		} catch (Exception ex) {
			LOG.error("Error while generating QrCode", ex);
			throw new RuntimeException("Error while generating QrCode", ex);
		}
	}
}