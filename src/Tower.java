import java.awt.Image;
import javax.swing.ImageIcon;

public class Tower {
    private String name;
    private Image towerImage;

    public Tower(String name, String imagePath) {
        this.name = name;
        this.towerImage = new ImageIcon(imagePath).getImage();  // Load image from the file path
    }

    public String getName() {
        return name;
    }

    public Image getTowerImage() {
        return towerImage;
    }


}
