package com.vadimrostov.uyutp.service;

import java.io.IOException;

public interface UPImageService {


    public String imageBase64Decoder(String format, String b64image)throws IOException;

    public String convertContent(String content)throws IOException;
}
