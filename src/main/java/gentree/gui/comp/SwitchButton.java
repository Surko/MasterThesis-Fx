package gentree.gui.comp;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.paint.Paint;

/**
 * Graficky vylepseny switch Button zalozeny na komponente label. Instanciu si je mozne vynutit 
 * konstruktorom s parametrom typu boolean ktory rozhodne default stav tohoto tlacidla.
 * Stav tlacidla si ukladame v polozke switchedOn typu SimpleBooleanProperty, ktora sluzi ako wrapper pre 
 * hodnoty boolean s pridanymi funkciami ako listenery, ktore reaguju na zmenu hodnot. Takyto druh listeneru
 * na tejto polozke vyuzivame pre graficku zmenu. V triede definujeme taktiez
 * button, ktory pri stlaceni zmeni polozku switchedOn a tym padom je zavolany jej listener. 
 * @author kirrie
 * @see SwitchButton#switchedOn
 * @see SwitchButton.SwitchListener
 * @see SwitchButton#init()
 */
public class SwitchButton extends Label {
	
	/**
	 * Polozka, v ktorej ukladame stav tohoto tlacidla. True stlaceny, false nestlaceny.
	 * Defaultnu hodnotu definujeme v konstruktore.
	 */
	private SimpleBooleanProperty  switchedOn;
	
	/**
	 * Trieda SwitchListener definuje ChangeListener, ktory mozno pridat k nejakej polozke.
	 * Implementujeme v nom metodu changed, ktora je zavolana pri zmene polozky. V pripade zmeny
	 * nastavujeme text tohoto tlacidla, umiestnenie tohoto textu, styl tlacidla ako aj vnutorny padding
	 * pre krajsi vyzor.
	 * @author kirrie
	 * @see ChangeListener
	 * @see SwitchButton#switchedOn
	 */
	private class SwitchListener implements ChangeListener<Boolean> {

		@Override
		public void changed(ObservableValue<? extends Boolean> observable,
				Boolean oldValue, Boolean newValue) {
			if (newValue) {
				setText("ON");
				setPadding(new Insets(0,0,0,7));
				setStyle("-fx-background-color:linear-gradient(rgb(92,192,92),rgb(78,165,78));-fx-font-weight:bold");
				setContentDisplay(ContentDisplay.RIGHT);
			} else {
				setText("OFF");
				setPadding(new Insets(0,7,0,0));
				setStyle("-fx-background-color:linear-gradient(rgb(70,70,70),rgb(50,50,50));-fx-font-weight:bold");
				setContentDisplay(ContentDisplay.LEFT);
			}		
		}
		
	}
	
	/**
	 * ClickedEvent implementujuci EventHandler pri vyvolani spracuje
	 * ActionEvent metodou handle, ktora zneguje polozku switchedOn
	 * (true na false | false na true)
	 * @author kirrie
	 * @see EventHandler
	 * @see SwitchButton#switchedOn
	 */
	private class ClickedEvent implements EventHandler<ActionEvent> {
		@Override
		public void handle(ActionEvent event) {
			switchedOn.set(!switchedOn.get());				
		}
	}
	
	/**
	 * Konstruktor pre tento objekt. Ako parameter dostavame switched typu boolean, ktory
	 * sluzi ako default hodnota pre polozku switchedOn. V prvom rade nastavime 
	 * polozku na negaciu hodnoty switched a to kvoli tomu aby pri nastaveni na
	 * ozajsku hodnotu switch bol vyvolany SwitchListener. Medzi tymito dvoma nastaveniami
	 * zinicializujeme tento komponent. 
	 * @param switched Defaultna hodnota akou bude nastavena polozka switchedOn
	 * @see SwitchButton#switchedOn
	 * @see SwitchButton#init()
	 */
	public SwitchButton(boolean switched) {	
		switchedOn = new SimpleBooleanProperty(!switched);
		init();
		switchedOn.set(switched);
	}
	
	/**
	 * Inicializacna metoda volana z konstruktora pre vytvorenie tohoto tlacidla. 
	 * Nastavujeme farbu textu, definujeme tlacidlo, ktorym menime stav polozky switchedOn pomocou 
	 * akcie ClickedEvent. Definujeme velkost tlacidla aby nevyzeralo ani moc male ani velke.
	 * Graficky vyzor docielujeme metodou setGraphic, ktora dostava tlacidlo switchBtn ako parameter.
	 * Tato metoda je typicka pre komponentu label a pouziva sa nato aby label obsahoval aj nieco ine nez
	 * len text (v tomto pripade plne funkcne tlacidlo). Nakoniec pridame listener SwitchListener
	 * reagujuci na zmenu pre polozku switchedOn.  
	 * @see SwitchButton#switchedOn
	 * @see SwitchButton.SwitchListener
	 * @see SwitchButton.ClickedEvent
	 */
	private final void init() {
		setTextFill(Paint.valueOf("white"));		
		Button switchBtn = new Button();
		switchBtn.setPrefWidth(35);
		// Pridanie listeneru ktory pocuva na stlacenie tlacidla a premeni hodnotu polozky switchedOn
		switchBtn.setOnAction(new ClickedEvent());
		
		// Nastaveny graficky objekt tohoto lablu. Graficky objekt moze byt lubovolny node.
		setGraphic(switchBtn);	
		// Pridany listener, ktory reaguje na zmenu tejto polozky.
		switchedOn.addListener(new SwitchListener());		
	}
	
	/**
	 * Metoda, ktora sluzi ako getter a navrati hodnotu polozky switchedOn 
	 * @return Aktualna hodnota switchedOn
	 */
	public SimpleBooleanProperty switchOnProperty() {
		return switchedOn;
	}
	
	
}
