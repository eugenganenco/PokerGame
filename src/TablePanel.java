import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;


// info displayed: pot, amount to call, community cards
public class TablePanel extends JPanel {

    private ArrayList<PlayerPanel> playerPanelList;
    private ArrayList<JLabel> cardsLabelList;
    private ImageIcon pokerTableIcon;
    private JLabel pokerTableLabel;
    private JLabel potLabel;
    private JLabel amountToCallLabel;
    private int numberOfPlayers;
    private PlayerPanel currentPlayerPanel;
    private PlayerPanel bigBlindPlayerPanel;
    private PlayerPanel smallBlindPLayerPanel;
    private final Insets insets;
    private final int card1LeftCoordinate = 424;
    private final int card1TopCoordinate = 312;
    private final int spaceBetweenCards = 46;
    private final int cardWidth = 45;
    private final int cardHeight = 75;



    public TablePanel(){

        playerPanelList = new ArrayList<>();
        cardsLabelList = new ArrayList<>();
        numberOfPlayers = 0;
        currentPlayerPanel = null;
        bigBlindPlayerPanel = null;
        smallBlindPLayerPanel = null;
        insets = this.getInsets();
        this.setLayout(null);
        this.setBackground(Color.GRAY);
        this.initializePotLabel();
        this.initializeAmountToCallLabel();

        this.displayTable();
        this.revalidate();




    }

    private void initializePotLabel(){
        this.potLabel = new JLabel("Pot: " );
        this.potLabel.setFont(new Font("Times New Roman",Font.PLAIN, 20));
        this.potLabel.setBounds(insets.left + 425, insets.top + 380, 150,60);
        this.add(this.potLabel);
    }
    private void initializeAmountToCallLabel(){
        amountToCallLabel = new JLabel("Amount To call:");
        amountToCallLabel.setFont(new Font("Times New Roman",Font.PLAIN, 20));
        amountToCallLabel.setBounds(insets.left + 425, insets.top + 425, 300,60);
        this.add(amountToCallLabel);
    }

    public void displayTable(){
        GuidingPanel guidingPanel = new GuidingPanel();
        Dimension guidingPanelSize = guidingPanel.getPreferredSize();
        guidingPanel.setBounds(insets.left + 980,insets.top+ 635,guidingPanelSize.width,guidingPanelSize.height);
        this.add(guidingPanel);

        for (int i = 0; i < 5; i++){
            JLabel cardLabel = new JLabel();
            cardsLabelList.add(cardLabel);
            cardLabel.setBounds((insets.left + card1LeftCoordinate) + spaceBetweenCards * i, insets.top + card1TopCoordinate,
                    cardWidth,cardHeight);
            this.add(cardLabel);
        }

        pokerTableIcon = new ImageIcon("table.png");
        pokerTableLabel = new JLabel();
        pokerTableLabel.setIcon(pokerTableIcon);
        Dimension imageSize = pokerTableLabel.getPreferredSize();
        pokerTableLabel.setBounds(insets.left,insets.top,imageSize.width,imageSize.height);
        this.add(pokerTableLabel);


    }

    public void addPlayer(String name){

        PlayerPanel newPlayerPanel = new PlayerPanel(name);
        PlayerPanelCoordinates playerPanelCoordinates = new PlayerPanelCoordinates();
        Dimension playerPanelDimension = newPlayerPanel.getPreferredSize();
        newPlayerPanel.setBounds(insets.left + playerPanelCoordinates.getCoordinates(numberOfPlayers).get(0),
                insets.top + playerPanelCoordinates.getCoordinates(numberOfPlayers).get(1),
                playerPanelDimension.width,playerPanelDimension.height);
        this.add(newPlayerPanel);
        playerPanelList.add(newPlayerPanel);
        numberOfPlayers++;
        this.revalidate();

    }

    public void updateCommunityCards(List<Card> communityCards){
        this.remove(pokerTableLabel);
        if (communityCards.size() == 0){
            for (int i=0;i < 5;i++){
                cardsLabelList.get(i).setIcon(null);
            }
        }
        else {

            System.out.println(communityCards);
            System.out.println(cardsLabelList.size());
            for (int i = 0; i < communityCards.size(); i++) {
                ImageIcon cardIcon = new ImageIcon(communityCards.get(i).getImageFileName());
                cardsLabelList.get(i).setIcon(cardIcon);

            }
        }
        this.displayTable();
        this.revalidate();
        this.repaint();




    }
    public int getNumberOfPlayers(){
        return numberOfPlayers;
    }

    public void highlightCurrentPlayer(String name){
        if (currentPlayerPanel != null) {
            if (currentPlayerPanel == smallBlindPLayerPanel) currentPlayerPanel.setSmallBlindBorder();
            else if (currentPlayerPanel == bigBlindPlayerPanel) currentPlayerPanel.setBigBlindBorder();
            else if (!currentPlayerPanel.hasFolded()) currentPlayerPanel.setDefaultBorder();
        }
        for (PlayerPanel playerPanel : playerPanelList) {
            if (playerPanel.getName().equals(name)) {
                currentPlayerPanel = playerPanel;
                currentPlayerPanel.setCurrentPlayerBorder();
            }
        }
    }
    public void ejectLosers(List<Player> losers){
        for (Player p : losers)
            for (PlayerPanel pp : playerPanelList)
                if (p.getName().equals(pp.getName()))
                    this.remove(pp);
        this.revalidate();
    }
    public void highlightCurrentPLayerAsFolded(){
        currentPlayerPanel.setFoldedBorder();
    }
    public void updateTheBlinds(List<Player> theBlinds){
        if (bigBlindPlayerPanel != null){
            smallBlindPLayerPanel.setDefaultBorder();
            bigBlindPlayerPanel.setDefaultBorder();
        }


        for (PlayerPanel p : playerPanelList){
            if (p.getName().equals(theBlinds.get(0).getName()))
                smallBlindPLayerPanel = p;
            else if (p.getName().equals(theBlinds.get(1).getName()))
                bigBlindPlayerPanel = p;
        }
        smallBlindPLayerPanel.setSmallBlindBorder();
        bigBlindPlayerPanel.setBigBlindBorder();

    }
    public void unfoldThePlayers(){
        for (PlayerPanel playerPanel : playerPanelList) {
            playerPanel.setDefaultBorder();
        }
    }

    public void updatePot(int pot){
        this.potLabel.setText("Pot: "+ pot);
    }
    public void updateAmountToCall(int amountToCall){
        this.amountToCallLabel.setText("Amount to call: " + amountToCall);
    }
    public void updateCurrentPLayerPanelChipsAmount(int newAmount){currentPlayerPanel.setChipsAmount(newAmount);}
    public void updateWinnersChips(List<Player> winnersList){
        for (Player p : winnersList){
            for (PlayerPanel pp : playerPanelList)
                if (p.getName().equals(pp.getName()))
                    pp.setChipsAmount(p.getChips());
        }
    }

}
