package com.vadimrostov.uyutp.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sun.misc.BASE64Decoder;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Service
@Transactional
public class UPImageServiceImpl implements UPImageService {

    public String imageBase64Decoder(String format, String b64image) throws IOException{
        byte[] array;
        UUID uuid=UUID.randomUUID();

            BASE64Decoder decoder = new BASE64Decoder();
            array = decoder.decodeBuffer(b64image);
            ByteArrayInputStream byteArrayInputStream=new ByteArrayInputStream(array);
            BufferedImage image= ImageIO.read(byteArrayInputStream);

            byteArrayInputStream.close();

            String filepath="d:/"+uuid+format;
            File file=new File("d:/"+uuid+format);
            ImageIO.write(image, format, file);
            return filepath;

    }

    public String convertContent(String content)throws IOException {
        StringBuilder builder=new StringBuilder();
        String img="<img src=\"data:image";
        boolean b=true;


        while (b){
            if (content.contains(img)){
                int builderone=content.indexOf(img);
                String builder1=content.substring(0, builderone);
                builder.append(builder1);

                int dd=content.indexOf("data-filename")-2;

                String allImg=content.substring(content.indexOf(img), content.indexOf("data-filename")-2);
                String builder2=allImg.substring(0, allImg.indexOf("base64")+6);
                String format=allImg.substring(allImg.indexOf("image/")+6, allImg.indexOf(";"));
                int aa=allImg.indexOf("base64")+7;
                int bb=allImg.indexOf("data-filename")-2;
                String b64img=allImg.substring(allImg.indexOf("base64")+7, allImg.length()-1);

                String formatPostfix=".jpg";
                if (format.equals("png")) formatPostfix=".png";
                else if (format.equals("jpeg")) formatPostfix=".jpg";
                else if (format.equals("gif")) formatPostfix=".gif";

                String filepath=imageBase64Decoder(formatPostfix, b64img);

                builder.append("<img src=\""+filepath+"\"");
                builder.append(" width=\"100%\">");

                String pods=content.substring(dd, content.length());
                content=pods.substring(pods.indexOf(">")+1, pods.length());
            }
            else {b=false;
                builder.append(content);}
        }

        return builder.toString();

    }
}
