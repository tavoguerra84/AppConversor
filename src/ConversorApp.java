import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

public class ConversorApp extends JFrame {
    private JLabel lblMonedaOrigen, lblMonedaDestino, lblCantidad, lblResultado;
    private JTextField txtCantidad, txtResultado;
    private JComboBox<String> cbMonedaOrigen, cbMonedaDestino;
    private JButton btnConvertir, btnConvertirKilometraje, btnConvertirTemperatura;

    private static final Map<String, Double> tasasDeCambio = new HashMap<>();
    static {
        tasasDeCambio.put("Dólares (USD)", 0.00026);
        tasasDeCambio.put("Euros (EUR)", 0.00022);
        tasasDeCambio.put("Libras Esterlinas (GBP)", 0.00018);
        tasasDeCambio.put("Yen Japonés (JPY)", 0.028);
        tasasDeCambio.put("Won Surcoreano (KRW)", 0.28);
    }

    public ConversorApp() {
        setTitle("Conversor de Moneda, Kilometraje y Temperatura");
        setSize(400, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(7, 2));

        lblMonedaOrigen = new JLabel("Moneda de Origen:");
        lblMonedaDestino = new JLabel("Moneda de Destino:");
        lblCantidad = new JLabel("Cantidad:");
        lblResultado = new JLabel("Resultado:");

        txtCantidad = new JTextField();
        txtResultado = new JTextField();
        txtResultado.setEditable(false);

        cbMonedaOrigen = new JComboBox<>(new String[]{"Pesos Colombianos (COP)", "Dólares (USD)", "Euros (EUR)", "Libras Esterlinas (GBP)", "Yen Japonés (JPY)", "Won Surcoreano (KRW)"});
        cbMonedaDestino = new JComboBox<>(new String[]{"Pesos Colombianos (COP)", "Dólares (USD)", "Euros (EUR)", "Libras Esterlinas (GBP)", "Yen Japonés (JPY)", "Won Surcoreano (KRW)"});

        btnConvertir = new JButton("Convertir Moneda");
        btnConvertir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                convertirMoneda();
            }
        });

        btnConvertirKilometraje = new JButton("Convertir Kilometraje");
        btnConvertirKilometraje.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                convertirKilometraje();
            }
        });

        btnConvertirTemperatura = new JButton("Convertir Temperatura");
        btnConvertirTemperatura.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                convertirTemperatura();
            }
        });

        add(lblMonedaOrigen);
        add(cbMonedaOrigen);
        add(lblMonedaDestino);
        add(cbMonedaDestino);
        add(lblCantidad);
        add(txtCantidad);
        add(lblResultado);
        add(txtResultado);
        add(new JLabel()); // Placeholder
        add(btnConvertir);
        add(new JLabel()); // Placeholder
        add(btnConvertirKilometraje);
        add(new JLabel()); // Placeholder
        add(btnConvertirTemperatura);
    }

    private void convertirMoneda() {
        try {
            String monedaOrigen = (String) cbMonedaOrigen.getSelectedItem();
            String monedaDestino = (String) cbMonedaDestino.getSelectedItem();
            double cantidad = Double.parseDouble(txtCantidad.getText());

            double tasaOrigen = obtenerTasaDeCambio(monedaOrigen);
            double tasaDestino = obtenerTasaDeCambio(monedaDestino);

            double resultado = cantidad * tasaDestino / tasaOrigen;

            DecimalFormat df = new DecimalFormat("#,###.##");
            txtResultado.setText(df.format(resultado));
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Entrada inválida. Por favor, ingrese un número válido.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private double obtenerTasaDeCambio(String moneda) {
        return tasasDeCambio.getOrDefault(moneda, 1.0);
    }

    private void convertirKilometraje() {
        try {
            double millas = Double.parseDouble(JOptionPane.showInputDialog(this, "Ingrese la cantidad de millas:", "Conversor de Kilometraje", JOptionPane.PLAIN_MESSAGE));
            double kilometros = millas * 1.60934;

            DecimalFormat df = new DecimalFormat("#.##");
            JOptionPane.showMessageDialog(this, millas + " millas son equivalentes a " + df.format(kilometros) + " kilómetros.", "Resultado", JOptionPane.INFORMATION_MESSAGE);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Entrada inválida. Por favor, ingrese un número válido.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void convertirTemperatura() {
        try {
            double celsius = Double.parseDouble(JOptionPane.showInputDialog(this, "Ingrese la temperatura en grados Celsius:", "Conversor de Temperatura", JOptionPane.PLAIN_MESSAGE));
            double fahrenheit = (celsius * 9 / 5) + 32;

            DecimalFormat df = new DecimalFormat("#.##");
            JOptionPane.showMessageDialog(this, celsius + " grados Celsius son equivalentes a " + df.format(fahrenheit) + " grados Fahrenheit.", "Resultado", JOptionPane.INFORMATION_MESSAGE);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Entrada inválida. Por favor, ingrese un número válido.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                ConversorApp app = new ConversorApp();
                app.setVisible(true);
            }
        });
    }
}


