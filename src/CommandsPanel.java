import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class CommandsPanel extends JPanel implements ActionListener {
    private final JButton addPlayerButton;
    private final JButton startButton;

    private final PlayerInfoCommandsPanel playerInfoPanel;
    private final HandPanel handPanel;
    private final ButtonsPanel buttonsPanel;

    private final JTextField playerNameTextField;
    private final Controller controller;
    private final TablePanel tablePanel;

    private Player currentPlayer;
    private final Set<String> namesSet = new HashSet<String>();

    public CommandsPanel(Controller controller,TablePanel tablePanel){
        this.controller = controller;
        this.tablePanel = tablePanel;

        playerInfoPanel = new PlayerInfoCommandsPanel();
        handPanel = new HandPanel();
        buttonsPanel = new ButtonsPanel(this);

        this.setLayout(new BorderLayout());
        this.setBackground(Color.GRAY);
        this.setPreferredSize(new Dimension(0,75));

        addPlayerButton = new JButton();
        addPlayerButton.setBounds(0,0,100,30);
        addPlayerButton.addActionListener(this);
        addPlayerButton.setText("Add player");
        addPlayerButton.setFocusable(false);


        startButton = new JButton();
        startButton.setBounds(0,0,100,30);
        startButton.addActionListener(this);
        startButton.setText("Start");
        startButton.setFocusable(false);
        startButton.setBorderPainted(false);

        playerNameTextField = new JTextField();
        playerNameTextField.setBounds(0,0,100,50);
        playerNameTextField.setText("Player name");

        this.add(playerNameTextField,BorderLayout.NORTH);
        this.add(addPlayerButton,BorderLayout.CENTER);
        this.add(startButton,BorderLayout.SOUTH);
    }




    public void setInGamePanel(){
        this.setPreferredSize(new Dimension(0,150));
        this.setBackground(Color.GRAY);
        this.remove(playerNameTextField);
        this.remove(addPlayerButton);
        this.remove(startButton);

        currentPlayer = controller.getCurrentPlayer();
        this.initializePanel(currentPlayer);
        this.revalidate();
    }

    public void initializePanel(Player player){
        this.setLayout(new BorderLayout());

        playerInfoPanel.setName(player.getName());
        playerInfoPanel.setChipsAmount(player.getChips());
        this.add(playerInfoPanel,BorderLayout.WEST);

        handPanel.updateHandPanel(player.getHand());
        this.add(handPanel,BorderLayout.CENTER);

        this.add(buttonsPanel,BorderLayout.SOUTH);
    }

    public void updatePanels(){
        playerInfoPanel.setName(currentPlayer.getName());
        playerInfoPanel.setChipsAmount(currentPlayer.getChips());
        handPanel.updateHandPanel(currentPlayer.getHand());
        tablePanel.highlightCurrentPlayer(currentPlayer.getName());
        tablePanel.updatePot(controller.getPot());
        tablePanel.updateAmountToCall(controller.getAmountToCall());
        tablePanel.updateCommunityCards(controller.getCommunityCards());
    }





    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addPlayerButton){
            if ((!namesSet.contains(playerNameTextField.getText()) && tablePanel.getNumberOfPlayers() < 10)) {
                namesSet.add(playerNameTextField.getText());
                controller.addPlayer(playerNameTextField.getText());
                tablePanel.addPlayer(playerNameTextField.getText());
                playerNameTextField.setText("");
                System.out.println(playerNameTextField.getText());

                if (tablePanel.getNumberOfPlayers() == 10)
                    addPlayerButton.setBorderPainted(false);
                if (tablePanel.getNumberOfPlayers() == 2)
                    startButton.setBorderPainted(true);
            }
            else {
                JOptionPane.showMessageDialog(null,"The name '" + playerNameTextField.getText() +
                        "' has already been taken. Try another one.");
                playerNameTextField.setText("");
            }
        }
        if (e.getSource() == startButton){
            if (tablePanel.getNumberOfPlayers() > 1) {
                controller.startGame();
                setInGamePanel();
                tablePanel.highlightCurrentPlayer(currentPlayer.getName());
                updatePanels();
            }


        }
        if (e.getSource() == buttonsPanel.getCallButton()){

            currentPlayer.call();
            tablePanel.updateCurrentPLayerPanelChipsAmount(currentPlayer.getChips());
            setCurrentPlayer(controller.getNextPlayer());
            updatePanels();

        }
        if (e.getSource() == buttonsPanel.getFoldButton()){
            currentPlayer.fold();
            tablePanel.highlightCurrentPLayerAsFolded();
            setCurrentPlayer(controller.getNextPlayer());
            updatePanels();

        }
        if (e.getSource() == buttonsPanel.getBetButton()){

            if (buttonsPanel.getAmountToBetTextField().getText().matches("^-?\\d+$")) {
                if (!(Integer.parseInt(buttonsPanel.getAmountToBetTextField().getText()) <= controller.getAmountToCall())) {
                    currentPlayer.bet(Integer.parseInt(buttonsPanel.getAmountToBetTextField().getText()));
                    tablePanel.updateCurrentPLayerPanelChipsAmount(currentPlayer.getChips());
                    setCurrentPlayer(controller.getNextPlayer());
                    updatePanels();
                }
                else JOptionPane.showMessageDialog(null,"Text field 'Amount to bet' should have a larger" +
                        " integer than the amount to call");
            }
            else JOptionPane.showMessageDialog(null,"Text field 'Amount to bet' should be an integer");
            buttonsPanel.setTextFieldNull();
        }
    }
    public void setCurrentPlayer(Player player){
        this.currentPlayer = player;
    }
    public void updateTheBlinds(List<Player> theBlinds){
        tablePanel.updateTheBlinds(theBlinds);
    }

}
