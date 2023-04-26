package stackx.cookbook.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import stackx.cookbook.api.model.Image;
import stackx.cookbook.api.repository.ImageRepository;

import java.io.IOException;

@RestController
@RequestMapping("/imageControl")
public class ImageController {

    @Autowired
    ImageRepository imgRep;


    @PostMapping("/saveImg")
    public Object saveImage (@RequestParam MultipartFile file) throws IOException {
         Image imageEntity = null;
         try {
             imageEntity.setUrlImg(file.getBytes());
             return imgRep.save(imageEntity);
         } catch (Exception e) {
             return e;
        }

    }
}
