package summary;
import java.awt.Font;
import summary.Sentence;
import summary.Word;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
public class Summarytool extends javax.swing.JFrame{
    public String filename; 
        ArrayList<Sentence> sentences;
        int noOfSentences;
        static ArrayList<Word> myword = new ArrayList<Word>();
        static FileReader file;
        static FileReader newfile;
        String ke,allwords;
        BufferedReader br;
        String[] sword;
        String showit = new String();
        int wi,p=0;
        int file_count=0,summary_count=0;
        double smax,compression;
        
        public Summarytool() {
        initComponents();
        }
        
        
         void init(String fn)
            {
                sentences = new ArrayList<Sentence>();
                 noOfSentences = 0;
                try
                { 
                        file = new FileReader(fn);
                        newfile = new FileReader(fn);
                        br = new BufferedReader(file);
                        }
                catch(FileNotFoundException e)
                {
                    e.printStackTrace();
                }
                catch(Exception e)
                {
                    e.printStackTrace();
                }
                extractSentenceFromContext();
            }
      void extractSentenceFromContext()
    {
            int nextChar,j=0;
            try{
                while((nextChar = br.read()) != -1)
                {
                    j=0;
                    char[] temp = new char[100000];
                    while((char)nextChar != '.')
                    {
                        temp[j]=(char)nextChar;
                        if((nextChar = br.read()) == -1)
                        {
                            break;
                        }
                            j++; 
                    }
                    String s = new String(temp).trim();
                  //  System.out.println(s);
                    String replaceAll = s.replaceAll("[\\n\\t ]", "");
                    for(int i=0;i<replaceAll.length();i++)
                    {
                        file_count++;  
                    }
                    sentences.add(new Sentence(noOfSentences,(new String(temp)).trim()));
                    noOfSentences++;
                }
            }catch(Exception e)
            {
                e.printStackTrace();
            }
            getfreq();  
    }           
    void getfreq()
         {  try{   
            FileReader fil = new FileReader("C:\\Users\\High Court\\Downloads\\Text-Summarizer-master\\Summary\\Stop words.txt");
            BufferedReader reader = new BufferedReader(fil);

            String key = "";
            String line = reader.readLine();

            while (line != null)
                {
                key += line;
                line = reader.readLine();
                }
                 BufferedReader readi = new BufferedReader(newfile);
                String ke = " ";
                String lin = readi.readLine();

                while (lin != null)
                    {
                    ke += lin;
                    lin = readi.readLine();
                    }
                    allwords=ke;
                    int l; 
                    String[] chk = key.split("\\s+");
                    int g = key.split("\\s+").length;
                    ke= ke.replace("."," . ");
                    ke= ke.replace(",","");
                    ke= ke.replace("\\n"," ");
                 for(l=0;l<g;l++)
                 {
                    String p = new String(" ");
                    p = p.concat(chk[l]);
                    p= p.concat(" ");

                    ke = ke.replace(p," ");
                 }
                 for(l=0;l<g;l++)
                 {
                    String p = new String(" ");
                    p = p.concat(chk[l]);
                    p= p.concat(" ");

                    ke = ke.replace(p," ");
                 }
                 ke.trim();
                 ke= ke.replace("  "," ");
               Map<String, Integer> frequency = new HashMap<String, Integer>();
            allwords=allwords.replace(","," ");
            allwords=allwords.replace("."," ");
            allwords=allwords.replace("?"," ");
            allwords=allwords.replace("!"," ");
            allwords=allwords.replace(")"," ");
            allwords=allwords.replace("("," ");
            allwords=allwords.replace("["," ");
            allwords=allwords.replace("["," ");
            allwords=allwords.replace(";"," ");
            allwords=allwords.replace("\n"," ");
        String[] arrw= allwords.split("\\s+");
        int lm= arrw.length,id,idr;
        
        for(int i=0;i<lm;i++)
        {
            arrw[i]=arrw[i].toLowerCase();
            for(int j=0;j<=i;j++)
            {
            id=arrw[i].indexOf(arrw[j]);
            idr=arrw[j].indexOf(arrw[i]);
          
            if((idr==0&&((arrw[i].concat("s")).equals(arrw[j])||(arrw[i].concat("es")).equals(arrw[j])||(arrw[i].concat("ed")).equals(arrw[j]))||
                (((arrw[j].concat("s")).equals(arrw[i]))||(arrw[j].concat("es")).equals(arrw[i])||(arrw[j].concat("ed")).equals(arrw[i]))&&id==0))
            {
                arrw[i]=arrw[j];
                break;
            }
            }
            if(frequency.containsKey(arrw[i]))
              {
                int val=frequency.get(arrw[i]);
                frequency.put(arrw[i],val+1);
              }
              else
               frequency.put(arrw[i],1);
            }
         for(String keyfile: frequency.keySet())
         {
            Word m = new Word(keyfile,frequency.get(keyfile));
            myword.add(m);
        }
    }
         catch(Exception e)
            {
                e.printStackTrace();
            }
         getdf();
         }
        void getdf()
    {   try
        {
            BufferedReader[] read = new BufferedReader[5];
            int i=0;
            String[] key = new String[5];
            String[] line = new String[5];
            FileReader[] file = new FileReader[5];
            file[0] = new FileReader("C:\\Users\\High Court\\Downloads\\Text-Summarizer-master\\Summary\\a.txt");
            file[1] = new FileReader("C:\\Users\\High Court\\Downloads\\Text-Summarizer-master\\Summary\\b.txt");
            file[2] = new FileReader("C:\\Users\\High Court\\Downloads\\Text-Summarizer-master\\Summary\\c.txt");
            file[3] = new FileReader("C:\\Users\\High Court\\Downloads\\Text-Summarizer-master\\Summary\\d.txt");
            file[4] = new FileReader("C:\\Users\\High Court\\Downloads\\Text-Summarizer-master\\Summary\\e.txt");
            for(i=0;i<5;i++)
            {
            read[i] = new BufferedReader(file[i]);
            key[i] = "";
            line[i] = read[i].readLine();

            while (line[i] != null)
                {
                key[i] += line[i];
                line[i] = read[i].readLine();
                }
            }            
                      for(i=0;i<5;i++)
            { 
                String[] chk2 = key[i].split("\\s+");
                for(Word k : myword)
                {
                    for(String kj : chk2)
                    {

                        if(k.value.compareToIgnoreCase(kj)==0)
                        {
                            k.df = k.df + 1.0;
                            break;
                        }
                    }
                }
            }
            for(Word k : myword)
            {
                k.df = k.df/6;
            }
        }
        catch(FileNotFoundException e){
                e.printStackTrace();
            }catch(Exception e){
                e.printStackTrace();
            }   
      tfid();
    }
      void tfid(){

            for(Word vb : myword)
            {
                vb.tfidf = vb.tf*(Math.log(1/vb.df));
            }
            output();
        } 
        void output()
        {
            try{
        for(Sentence sen: sentences)
        {
            sword=sen.value.split("\\s+");
            wi=0;
            for(int j=0;j<sword.length;j++)
            {
                for(Word k:myword)
                {
                    if(sword[j].equalsIgnoreCase(k.value)||sword[j].concat("s").equalsIgnoreCase(k.value)||sword[j].concat("es").equalsIgnoreCase(k.value)||sword[j].concat("ed").equalsIgnoreCase(k.value)||
                    sword[j].equalsIgnoreCase(k.value.concat("s"))|| sword[j].equalsIgnoreCase(k.value.concat("es"))|| sword[j].equalsIgnoreCase(k.value.concat("ed")))
                    {
                        sen.score=sen.score+k.tfidf;
                        if(k.tfidf>0.0)
                        wi++;
                    }
                }
            }
            sen.score=sen.score/wi;
        };
         int rem= sentences.size()%7;
        rem -= 1;
        int [] arrsum= new int[sentences.size()];
        smax=0;
        int i;
        int ss=-1,lsen=sentences.get(sentences.size()-1).number;
        p=0;
        arrsum[p]=0;
        p++;
        for( i=1;i<sentences.size()-rem;i+=5)
        {
            sort(sentences,i,4);
            
            
            if(smax>sentences.get(i).score)
            {
                smax=sentences.get(i).score;
                arrsum[p]=ss;p++;
                ss=sentences.get(i).number;
            }
            else
            {
            arrsum[p]=sentences.get(i).number;p++;
            smax=sentences.get(i+1).score;
            ss= sentences.get(i+1).number;
        }
        }
        if(rem<=0)
        {
            if(arrsum[p-1]!=lsen)
             {
                 arrsum[p]=lsen;
             }
            }
        if(rem==1)
        {
            arrsum[p]=lsen;
        }
        if(rem>1)
        {
            sort(sentences,i,rem-1);
            if(smax>sentences.get(i).score)
            {
                arrsum[p]=ss;p++;
                arrsum[p]=lsen;
            }
            else
            {
             arrsum[p]=sentences.get(i).number;p++;
             if(arrsum[p-1]!=lsen)
             {
                 arrsum[p]=lsen;
             }
            }
        }
      for(int j=0;j<p;j++)
      {  
       for (int k = 0; k < p-j; k++)
       {
           if (arrsum[k]>arrsum[k+1])
           {   
              int temp2=arrsum[k];
              arrsum[k]=arrsum[k+1];
              arrsum[k+1] =temp2;
            }
        }
    }
     for(int x=0;x<=p;x++)
     {
         for(int q=0;q<sentences.size();q++)
         {
              if(arrsum[x]==sentences.get(q).number)
            
              {
                showit = showit.concat(sentences.get(q).value+". ");
              } 
            }
        }
     summary_count=showit.length();
     System.out.println("Number of characteres in summary: "+summary_count);
     System.out.println("Number of characteres in input file: "+file_count);
    compression=(summary_count*100)/file_count;
    System.out.println("Compression of original text to summary (in %) = "+compression+"%");
    jLabel6.setFont(new java.awt.Font("TimesRoman",  2, 20));
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
     jLabel6.setText("Compression of original text to summary (in %) = "+compression+"%");
        jLabel6.setToolTipText("");
        jLabel6.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
    Show_Output.setText(showit);
    }
            catch(Exception e)
            {
                e.printStackTrace();
            }

        }
     void sort(ArrayList<Sentence> sente,int i,int n)
   {
  for(int j = 0;j<n;j++)
    {
        
            for (int k = i; k<i+n-j ; k++)
            {
                if(k>=sentences.size()-1)
                break;
                if (sente.get(k).score <sente.get(k+1).score)
                {
                    
                    double temp = sente.get(k).score;
                    String temp2=sente.get(k).value;
                    int temp3=sente.get(k).number;
                     sente.get(k).score =  sente.get(k+1).score;
                    sente.get(k).value=sente.get(k+1).value;
                    sente.get(k).number=sente.get(k+1).number;
                     sente.get(k+1).score = temp;
                    sente.get(k+1).value=temp2;
                    sente.get(k+1).number=temp3;
                }
            }
                    }
                }
    @SuppressWarnings("unchecked")                       
    private void initComponents() {
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel(); 
        scrollPane1 = new java.awt.ScrollPane();
        jScrollBar1 = new javax.swing.JScrollBar();
        jLabel6 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        Input = new java.awt.TextField();
        jLabel9 = new javax.swing.JLabel();
        Browse_Button = new javax.swing.JButton();
        Open_Butten = new javax.swing.JButton();
        compress_Butten = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        Show_Output = new javax.swing.JTextArea();
        filler1 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(32767, 32767));
        jLabel2.setText("jLabel2");
        jLabel3.setText("jLabel3");
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("TEXT SUMMARIZER");
        jPanel2.setBackground(new java.awt.Color(255, 165, 0));
        jLabel1.setFont(new java.awt.Font("TimesRoman",  3, 44)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel1.setText("!!! TEXT SUMMARIZER !!!");
        jLabel1.setToolTipText("");
        jLabel1.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        jLabel4.setBackground(new java.awt.Color(255, 165, 0));
        jLabel4.setFont(new java.awt.Font("Bookman Old Style", 2, 13));
        jLabel4.setForeground(new java.awt.Color(0,0,0));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("We are ready to summarize your text:)");
        jLabel4.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 562, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 962, javax.swing.GroupLayout.PREFERRED_SIZE)
                )
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE)
                .addContainerGap())
                .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE)
        );
        jLabel5.setFont(new java.awt.Font("Tahoma", 2, 18)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel5.setText("SUMMARY:-");
        jLabel5.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 15));
        jLabel7.setText("Enter file path:-");
        jLabel8.setIcon(new javax.swing.ImageIcon("Search.png"));
        Input.setFont(new java.awt.Font("Dialog", 0, 18)); 
        Input.setText("Choose the file location");
        Input.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                InputActionPerformed(evt);
            }
        });
        jLabel9.setIcon(new javax.swing.ImageIcon("text.png")); 
        Browse_Button.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); 
        Browse_Button.setText("Browse");
        Browse_Button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Browse_ButtonMouseClicked(evt);
            }
        });
        Browse_Button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Browse_ButtonActionPerformed(evt);
            }
        });
        Open_Butten.setFont(new java.awt.Font("Tahoma", 0, 18)); 
        Open_Butten.setText("Open");
        Open_Butten.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Open_ButtenMouseClicked(evt);
            }
        });
        Open_Butten.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Open_ButtenActionPerformed(evt);
            }
        });
        Show_Output.setColumns(20);
        Show_Output.setFont(new java.awt.Font("Monospaced", 0, 18)); // NOI18N
        Show_Output.setLineWrap(true);
        Show_Output.setRows(5);
        jScrollPane1.setViewportView(Show_Output);
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(101, 101, 101)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel9)
                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(Input, javax.swing.GroupLayout.PREFERRED_SIZE, 328, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(Browse_Button, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(Open_Butten, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(47, 47, 47)
                        .addComponent(filler1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 977, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE)))))
                .addGap(46, 46, 46))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(51, 51, 51)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(Browse_Button, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(Open_Butten, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(Input, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 463, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(256, 256, 256)
                        .addComponent(filler1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(42, 42, 42))
        );
        pack();
    }
    private void InputActionPerformed(java.awt.event.ActionEvent evt) {                                      
    }                                     
    private void Browse_ButtonActionPerformed(java.awt.event.ActionEvent evt) {                                              
    }                                             
    private void Browse_ButtonMouseClicked(java.awt.event.MouseEvent evt) {                                           
        JFileChooser Chooser = new JFileChooser();
        Chooser.showOpenDialog(null);
           File ffile = Chooser.getSelectedFile();
           filename = ffile.getAbsolutePath();
           Input.setText(filename); 
    }                                          
    private void Open_ButtenActionPerformed(java.awt.event.ActionEvent evt) {                                              
    }                                           

    private void Open_ButtenMouseClicked(java.awt.event.MouseEvent evt) {                                         
        init(filename);
    }                                        
    public static void main(String[] args) 
    {
        try 
        {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels())
            {
                if ("Nimbus".equals(info.getName())) 
                {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        }
        catch (ClassNotFoundException ex)
        {
            java.util.logging.Logger.getLogger(Summarytool.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        catch (InstantiationException ex)
        {
            java.util.logging.Logger.getLogger(Summarytool.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        catch (IllegalAccessException ex) 
        {
            java.util.logging.Logger.getLogger(Summarytool.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        catch (javax.swing.UnsupportedLookAndFeelException ex) 
        {
            java.util.logging.Logger.getLogger(Summarytool.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        java.awt.EventQueue.invokeLater(new Runnable() 
        {
            public void run() 
            {
                new Summarytool().setVisible(true);
            }
        });  
    }                  
    public javax.swing.JButton Browse_Button;
    public java.awt.TextField Input;
    public javax.swing.JButton Open_Butten;
    public javax.swing.JButton compress_Butten;
    public javax.swing.JTextArea Show_Output;
    private javax.swing.Box.Filler filler1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollBar jScrollBar1;
    private javax.swing.JScrollPane jScrollPane1;
    private java.awt.ScrollPane scrollPane1;   
}
