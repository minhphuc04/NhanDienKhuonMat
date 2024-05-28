package Recognizer;
import Util.ConnectBanco;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.sql.Array;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;
import org.bytedeco.javacpp.BytePointer;
import org.bytedeco.javacpp.DoublePointer;
import org.bytedeco.javacpp.IntPointer;
import static org.bytedeco.opencv.global.opencv_imgcodecs.imencode;
import org.bytedeco.opencv.global.opencv_imgproc;
import static org.bytedeco.opencv.global.opencv_imgproc.COLOR_BGR2GRAY;
import static org.bytedeco.opencv.global.opencv_imgproc.rectangle;
import org.bytedeco.opencv.opencv_core.Mat;
import org.bytedeco.opencv.opencv_core.Rect;
import org.bytedeco.opencv.opencv_core.RectVector;
import org.bytedeco.opencv.opencv_core.Scalar;
import org.bytedeco.opencv.opencv_core.Size;
import org.bytedeco.opencv.opencv_face.FaceRecognizer;
import org.bytedeco.opencv.opencv_face.LBPHFaceRecognizer;
import org.bytedeco.opencv.opencv_objdetect.CascadeClassifier;
import org.bytedeco.opencv.opencv_videoio.VideoCapture;
public class Recognizer extends javax.swing.JFrame {
        private Recognizer.DaemonThread myThread = null;
    VideoCapture webSource = null;
    Mat cameraImage = new Mat();
    CascadeClassifier cascade = new CascadeClassifier("C:\\photos\\haarcascade_frontalface_alt.xml");
    
        FaceRecognizer recognizer = LBPHFaceRecognizer.create();

    BytePointer mem = new BytePointer();
    RectVector detectedFaces = new RectVector();
    String root, firstNamePerson, lastNamePerson, officePerson, dobPerson;
    int idPerson ;
    ConnectBanco conecta = new ConnectBanco();



    public Recognizer() {
        initComponents();
        recognizer.read("C:\\photos\\classifierLBPH.yml");
        recognizer.setThreshold(80);
        startCamera();
        
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        label_photo = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        label_name = new javax.swing.JLabel();
        labelOffice = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Security  System - Recognizer Person");
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        label_photo.setBackground(new java.awt.Color(255, 255, 255));
        label_photo.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

        javax.swing.GroupLayout label_photoLayout = new javax.swing.GroupLayout(label_photo);
        label_photo.setLayout(label_photoLayout);
        label_photoLayout.setHorizontalGroup(
            label_photoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 348, Short.MAX_VALUE)
        );
        label_photoLayout.setVerticalGroup(
            label_photoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 368, Short.MAX_VALUE)
        );

        jPanel1.add(label_photo, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 350, 370));

        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 400, 350, -1));

        label_name.setBackground(new java.awt.Color(204, 0, 204));
        label_name.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        label_name.setForeground(new java.awt.Color(255, 255, 255));
        label_name.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label_name.setText("FIRST - Lastname");
        label_name.setBorder(new javax.swing.border.MatteBorder(null));
        label_name.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        label_name.setOpaque(true);
        jPanel1.add(label_name, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 390, 320, 40));

        labelOffice.setBackground(new java.awt.Color(51, 255, 51));
        labelOffice.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        labelOffice.setForeground(new java.awt.Color(255, 255, 255));
        labelOffice.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelOffice.setText("Offce");
        labelOffice.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        labelOffice.setOpaque(true);
        jPanel1.add(labelOffice, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 440, 320, 40));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 380, 510));

        setSize(new java.awt.Dimension(405, 523));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents


    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Recognizer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Recognizer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Recognizer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Recognizer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Recognizer().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JLabel labelOffice;
    private javax.swing.JLabel label_name;
    private javax.swing.JPanel label_photo;
    // End of variables declaration//GEN-END:variables

class DaemonThread implements Runnable {
    protected volatile boolean runnable = false;

    @Override
   public void run() {
            synchronized (this) {
                while (runnable) {
                    try {
                        if (webSource.grab()) {
                            webSource.retrieve(cameraImage);
                            Graphics g = label_photo.getGraphics();
                            if (g != null) {
                                Mat imageColor = cameraImage;
                                Mat imageGray = new Mat();//hahahahaha
                                opencv_imgproc.cvtColor(imageColor, imageGray, COLOR_BGR2GRAY);
                                RectVector detectedFace = new RectVector();
                                cascade.detectMultiScale(imageGray, detectedFace, 1.1, 2, 0, new Size(150, 150), new Size(500, 500));
                                //Ve hinh cn
                                for (int i = 0; i < detectedFace.size(); i++) {
                                    Rect dadosFace = detectedFace.get(i);
                                    rectangle(cameraImage, dadosFace, new Scalar(0, 255, 0, 0));
                                    //Nhan dien
                                    Mat faceCapturada = new Mat(imageGray, dadosFace);
                                    opencv_imgproc.resize(faceCapturada, faceCapturada, new Size(160, 160));
                                    IntPointer rotulo = new IntPointer(1);
                                    DoublePointer confidence = new DoublePointer(1);
                                    recognizer.predict(faceCapturada, rotulo, confidence);
                                    int prediction = rotulo.get(0);
                                    if (prediction == -1) {
                                        label_name.setText("Không nhận diện được");
                                        labelOffice.setText("");
                                        idPerson = 0;
                                    } else {
                                        System.out.println(confidence.get(0));
                                        idPerson = prediction;
                                        rec();
                                    }
                                }
                                //Hien thi hinh anh
                                imencode(".bmp", cameraImage, mem);
                                Image im = ImageIO.read(new ByteArrayInputStream(mem.getStringBytes()));
                                BufferedImage buff = (BufferedImage) im;
                                if (g.drawImage(buff, 0, 0, getWidth(), getHeight() - 100, 0, 0, buff.getWidth(), buff.getHeight(), null)) {
                                    if (!runnable) {
                                        System.out.println("Salve a Foto");
                                        this.wait();
                                    }
                                }
                            } else {
                                System.out.println("Graphics object is null.");
                            }
                        }
                    } catch (IOException ex) {
                        ex.printStackTrace();
                        JOptionPane.showMessageDialog(null, "Error ao iniciar camera (IOException)\n" + ex);
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                        JOptionPane.showMessageDialog(null, "Error ao iniciar camera (InterruptedException)\n" + ex);
                    }
                }
            }
        }
}


private void rec() {
    SwingWorker<Void, Void> worker = new SwingWorker<>() {
        @Override
        protected Void doInBackground() throws Exception {
            conecta.conexao();
            String SQL = "SELECT * FROM person WHERE id = ?";
            try (PreparedStatement stmt = conecta.conn.prepareStatement(SQL)) {
                stmt.setInt(1, idPerson);
                conecta.rs = stmt.executeQuery();
                if (conecta.rs.next()) {
                    String firstName = conecta.rs.getString("first_name");
                    String lastName = conecta.rs.getString("last_name");
                    String office = conecta.rs.getString("office");
                    String dob = conecta.rs.getString("dob");

                    SwingUtilities.invokeLater(() -> {
                        label_name.setText(firstName + " " + lastName);
                        labelOffice.setText(office);
                    });

                    // Kiểm tra xem ResultSet có đóng trước khi gọi phương thức getInt("id") hay không
                    if (!conecta.rs.isClosed()) {
                        System.out.println("Person: " + conecta.rs.getInt("id"));
                        System.out.println("hahaha: " + lastName );
                    }
                }
            } catch (SQLException e) {
                System.err.println("SQL Exception: " + e.getMessage());
                e.printStackTrace();
            } finally {
                if (conecta.rs != null && !conecta.rs.isClosed()) {
                    conecta.rs.close();
                }
                conecta.desconecta();
            }
            return null;
        }
    };
    worker.execute();
}






    private void stopCamera() {
        myThread.runnable = false;
        webSource.release();
        dispose();
    }

    private void startCamera() {
        webSource = new VideoCapture(0);
        myThread = new Recognizer.DaemonThread();
        Thread t = new Thread(myThread);
        t.setDaemon(true);
        myThread.runnable = true;
        t.start();
    }



}
