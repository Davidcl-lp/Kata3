package software.ulpgc.kata3;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.statistics.HistogramDataset;
import org.jfree.data.statistics.HistogramType;

import java.util.List;
import java.util.Map;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        List<Person> people = TsvFilePersonLoader.with("src/main/resources/hw_25000.tsv").load();

        HistogramDataset dataset = new HistogramDataset();
        dataset.setType(HistogramType.FREQUENCY);

        double[] weights = people.stream().mapToDouble(Person::getWeight).toArray();
        dataset.addSeries("Weight", weights, WeightHistogramCalculator.BIN_SIZE);
        JFreeChart histogram = ChartFactory.createHistogram(
                "Histograma de Pesos",
                "Peso",
                "Personas",
                dataset,
                PlotOrientation.VERTICAL,
                true,
                false,
                false
        );
        showHistogram(histogram);
    }
    private static void showHistogram(JFreeChart chart){
        JFrame frame = new JFrame();
        frame.setTitle("Histograma de Peso");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(new ChartPanel(chart));
        frame.pack();
        frame.setVisible(true);
    }
}
