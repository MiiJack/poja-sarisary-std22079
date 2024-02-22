package hei.school.sarisary.repository.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import java.sql.Timestamp;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
public class ImageTransformation {
  @Id private String id;

  @Column(name = "image_path")
  String imagePath;

  @Column(name = "transformation_image_path")
  String transformationStatus;

  @Column(name = "transformation_timestamp")
  Timestamp transformationTimestamp;
}
