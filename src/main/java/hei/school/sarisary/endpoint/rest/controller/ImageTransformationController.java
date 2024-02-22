package hei.school.sarisary.endpoint.rest.controller;

import hei.school.sarisary.file.BucketComponent;
import hei.school.sarisary.repository.ImageTransformationRepository;
import hei.school.sarisary.service.ImageTransformationService;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@AllArgsConstructor
public class ImageTransformationController {
  private BucketComponent bucketComponent;
  ImageTransformationRepository iTR;
  @Autowired private ImageTransformationService iTS;

  public static final String directory = "images/";

  @RequestMapping(
      path = "/black-and-white/{id}",
      method = RequestMethod.PUT,
      consumes = {MediaType.IMAGE_PNG_VALUE})
  public ResponseEntity<Map<String, String>> toGrayscale(
      @PathVariable(name = "id") String id, @RequestParam(name = "file") MultipartFile image)
      throws IOException {
    String fileSuffix = ".png";
    String filePrefix = id + "-original";
    String transformedFilePrefix = id + "-grayscale";

    File originalImage = File.createTempFile(filePrefix, fileSuffix);
    image.transferTo(originalImage);

    String originalBucketKey = directory + originalImage.getName();
    bucketComponent.upload(originalImage, originalBucketKey);

    File transformedImage = iTS.Grayscale(originalImage);
    String transformedBucketKey = directory + transformedImage.getName();
    bucketComponent.upload(transformedImage, transformedBucketKey);

    URL originalURL = bucketComponent.presign(originalBucketKey, Duration.ofMinutes(18));
    URL transformedURL = bucketComponent.presign(transformedBucketKey, Duration.ofMinutes(18));

    Map<String, String> response = new HashMap<>();
    response.put("original_url", originalURL.toString());
    response.put("transformed_url", transformedURL.toString());

    return ResponseEntity.ok(response);
  }
}
