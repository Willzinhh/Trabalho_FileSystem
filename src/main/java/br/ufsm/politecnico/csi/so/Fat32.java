package br.ufsm.politecnico.csi.so;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

public class Fat32 implements FileSystem {

    private Disco disco = new Disco();
    private int[] fat = new int[Disco.NUM_BLOCOS];
    private static final int BLOCO_FAT = 0;
    private static final int BLOCO_DIRETORIO = 1;
    private static final int BLOCO_OCUPADO = -1;

    public Fat32(Disco disco) throws IOException {
        this.disco = disco;
        if (!disco.init()){
            inicaializaFat();
            inicaializaDiretorio();
        }else {
            leFat();
            leDiretorio();
        }
    }

    private void leDiretorio() {
    }

    // le a Fat que está no Disco
    private void leFat() throws IOException {
        byte[] bloco = disco.read(BLOCO_FAT);
        ByteBuffer buffer = ByteBuffer.wrap(bloco);
        for (int i = 0; i < fat.length ; i++) {
            int n = buffer.getInt();
            fat[i] = n;
        }
    }

    private void inicaializaDiretorio() {
    }

    private void inicaializaFat() throws IOException {
       fat[BLOCO_FAT ] = BLOCO_OCUPADO;
       fat[BLOCO_DIRETORIO] = BLOCO_OCUPADO;
        gravaFat();
    }

    private void gravaDiretorio() {

    }

    private void gravaFat() throws IOException {
        ByteBuffer buffer = ByteBuffer.allocate(Disco.NUM_BLOCOS);
        for (int i : fat) {
            buffer.putInt(i);

        }
        disco.write(BLOCO_FAT, buffer.array());
    }

    @Override
    public void create(String fileName, byte[] data) {


    }

    @Override
    public void append(String fileName, byte[] data) {

    }

    @Override
    public byte[] read(String fileName, int offset, int limit) {
        

        //1. Buscar o arquivo no diretorio pelo nome.
        /**
         *          numBloco = diretorio.blocoinicial;
         *          byte[] arquivo = new byte[diretorio.lenth]
         *          do{
         *              leio o bloco
         *              gravo o bloco no arquivo[]
         *              numBloco = fat[numBloco]
         *          }while(numBloco > 0
         * **/

        return new byte[0];
    }

    public List<String> listaArquivos() {
        return null;
    }

    @Override
    public void remove(String fileName) {

    }

    @Override
    public int freeSpace() {
        int livres = 0;
        for (int i : fat){
            if (i == 0){
                livres++;
            }
        }
        return livres * Disco.TAM_BLOCO;
    }

    private List<EntradaDiretorio> diretorio = new ArrayList<>();


    // um para cada arquivo
    private class EntradaDiretorio {
        public String fileName;
        public int tamanhoArquivo;
        public int blocoInicial;


    }
}
