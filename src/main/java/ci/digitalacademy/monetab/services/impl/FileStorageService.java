package ci.digitalacademy.monetab.services.impl;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class FileStorageService implements ci.digitalacademy.monetab.services.FileStorageService {

        private  final Cloudinary cloudinary;

        public String upload(MultipartFile file) throws IOException {

// Upload the image
            Map params1 = ObjectUtils.asMap(
                    "use_filename", true,
                    "unique_filename", true,
                    "overwrite", true
            );

            Map upload = cloudinary.uploader().upload(file.getBytes(), params1);
            System.out.println(upload);
            return upload.get("url").toString();
        }

}

