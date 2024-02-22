package hei.school.sarisary.service;

import hei.school.sarisary.repository.ImageTransformationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

@Service
public class ImageTransformationService {
  @Autowired
  private final ImageTransformationRepository imageTransformationRepository;

  public ImageTransformationService(ImageTransformationRepository imageTransformationRepository) {
    this.imageTransformationRepository = imageTransformationRepository;
  }

  public BufferedImage Grayscale(File imageToTransform) throws IOException {
    // Read the image from the MultipartFile into a BufferedImage
    BufferedImage img = ImageIO.read(imageToTransform);
    int width = img.getWidth();
    int height = img.getHeight();
    // Convert the image to grayscale
    BufferedImage grayscaleImage = new BufferedImage(width, height, BufferedImage.TYPE_BYTE_GRAY);
    for (int i = 0; i < width; i++) {
      for (int j = 0; j < height; j++) {
        int rgb = img.getRGB(i, j);
        int r = (rgb >> 16) & 0xFF;
        int g = (rgb >> 8) & 0xFF;
        int b = rgb & 0xFF;
        int gray = (r + g + b) / 3;
        grayscaleImage.setRGB(i, j, (gray << 16) | (gray << 8) | gray);
      }
    }
    return grayscaleImage;
  }
}
