package stackx.cookbook.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;
import stackx.cookbook.api.model.Image;
import stackx.cookbook.api.repository.ImageRepository;

import java.io.IOException;

@RestController
@RequestMapping("/upload")
public class ImageController {

    @Autowired
    ImageRepository imgRep;

    @PostMapping
    Long uploadImage(@RequestParam("img") MultipartFile multipartImage) throws IOException {
        Image dbImage = new Image();

        dbImage.setNameFile(multipartImage.getName());
        dbImage.setContent(multipartImage.getBytes());

        return imgRep.save(dbImage).getId();
    }

    @GetMapping(value = "/image/{imageId}", produces = MediaType.IMAGE_JPEG_VALUE)
    ByteArrayResource downloadImagebyId(@PathVariable Long imageId) {
        byte[] image = imgRep.findById(imageId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND))
                .getContent();

        return new ByteArrayResource(image);
    }

}
