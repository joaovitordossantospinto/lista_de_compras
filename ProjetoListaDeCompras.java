import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;

public class ProjetoListaDeCompras extends Application{
	private ArrayList<String> listaDeCompras = new ArrayList<>();
	private ListView<String> listaVisualizavel = new ListView<>();

	@Override
	public void start(Stage palco){
		palco.setTitle("Aplicativo de Lista de Compras");

		TextField textFieldDescricaoItem = new TextField();
		Button botaoAdcionar = new Button("Adicionar");
		Button botaoExportar = new Button("Exportar");

		Label labelAdicionar = new Label ("Digite o item que deseja adicionar");
		Label labelListaDeCompras = new Label ("Lista de compras: ");

		ObservableList<String> observableListaDeCompras = FXCollections.observableArrayList(listaDeCompras);
		listaVisualizavel.setItems(observableListaDeCompras);

		VBox vbox = new VBox();
		vbox.getChildren().addAll(labelAdicionar, textFieldDescricaoItem, botaoAdcionar);
		vbox.getChildren().addAll(labelListaDeCompras, listaVisualizavel, botaoExportar);
		vbox.setSpacing(10);
		vbox.setPadding(new Insets(10));

		botaoAdcionar.setOnAction(e -> {
			String item = textFieldDescricaoItem.getText();
			if(!item.isEmpty()){
				listaDeCompras.add(item);
				listaVisualizavel.getItems().add(item);
				textFieldDescricaoItem.clear();
			}
		});

		botaoExportar.setOnAction(e -> {
			try {
				File arquivo = new File("ListaDeCompras.txt");
				PrintWriter writer = new PrintWriter(arquivo);
				for (String item : listaDeCompras){
					writer.println(item);
				}
				writer.close();
			} catch (Exception ex){
				System.out.println("Erro ocorrido: " + ex.getMessage());
			}
		});

		Scene scene = new Scene (vbox, 350, 300);
		palco.setScene(scene);
		palco.show();

	}

	public static void main (String[] args){
		launch(args);
	}
}