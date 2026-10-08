package com.tutego.date4u.interfaces.shell;

import com.tutego.date4u.core.photo.PhotoService;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

import javax.imageio.ImageIO;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@ShellComponent
public class PhotoCommands {

  private final PhotoService photoService;

  public PhotoCommands( PhotoService photoService ) {
    this.photoService = photoService;
  }

  @ShellMethod( "Display image dimensions" ) String imageSize( String name ) {// image-size
    return photoService.download( name ).map( bytes -> {
      try {
        var image = ImageIO.read( new ByteArrayInputStream( bytes ) );
        return "Width: %d, Height: %d".formatted( image.getWidth(), image.getHeight() );
      }
      catch ( IOException e ) {
        return "Unable to read image dimensions";
      }
    } ).orElse( "Image not found" );
  }

  @ShellMethod( "Upload photo" )  // upload-photo
  String uploadPhoto( String filename ) throws IOException {
    byte[] bytes = Files.readAllBytes( Paths.get( filename ) );
    return "Uploaded " + photoService.upload( bytes );
  }

  //  @Autowired Validator validator;
  //
  //  @ShellMethod( "Inserts a new photo" )
  //  String insertPhoto( Long id, long profile, String name,
  //                      boolean isProfilePhoto, String created
  //  ) {
  //    Photo photo = new Photo( id, profile, name, isProfilePhoto,
  //                             LocalDateTime.parse( created ) );
  //    Set<ConstraintViolation<Photo>> violationSet = validator.validate( photo );
  //    // Insert photo into database if violationSet.isEmpty()
  //    return violationSet.isEmpty() ? "Photo inserted"
  //                                  : "Photo not inserted:\n" + violationSet;
  //  }
}
