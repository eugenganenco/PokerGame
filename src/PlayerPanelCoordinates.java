import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

public class PlayerPanelCoordinates {
    private Hashtable<Integer, List<Integer>> panelCoordinates;
    public PlayerPanelCoordinates(){
        panelCoordinates = new Hashtable<>();
        List<Integer> coordinates = new ArrayList<>(2);
        coordinates.add(640);
        coordinates.add(575);
        panelCoordinates.put(0,coordinates);

        coordinates = new ArrayList<>(2);
        coordinates.add(340);
        coordinates.add(575);
        panelCoordinates.put(1,coordinates);

        coordinates = new ArrayList<>(2);
        coordinates.add(109);
        coordinates.add(525);
        panelCoordinates.put(2,coordinates);

        coordinates = new ArrayList<>(2);
        coordinates.add(33);
        coordinates.add(298);
        panelCoordinates.put(3,coordinates);

        coordinates = new ArrayList<>(2);
        coordinates.add(109);
        coordinates.add(43);
        panelCoordinates.put(4,coordinates);

        coordinates = new ArrayList<>(2);
        coordinates.add(340);
        coordinates.add(0);
        panelCoordinates.put(5,coordinates);

        coordinates = new ArrayList<>(2);
        coordinates.add(640);
        coordinates.add(0);
        panelCoordinates.put(6,coordinates);

        coordinates = new ArrayList<>(2);
        coordinates.add(873);
        coordinates.add(43);
        panelCoordinates.put(7,coordinates);

        coordinates = new ArrayList<>(2);
        coordinates.add(934);
        coordinates.add(298);
        panelCoordinates.put(8,coordinates);

        coordinates = new ArrayList<>(2);
        coordinates.add(886);
        coordinates.add(500);
        panelCoordinates.put(9,coordinates);
    }
    public List<Integer> getCoordinates(int playerNumber){
        return panelCoordinates.get(playerNumber);
    }
}
