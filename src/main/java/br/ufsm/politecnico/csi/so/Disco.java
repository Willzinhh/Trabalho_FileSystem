package br.ufsm.politecnico.csi.so;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

public class Disco {

    public static final int TAM_BLOCO = 64 * 1024;
    public static final int NUM_BLOCOS = 1024;
    private RandomAccessFile raf;

    public Disco() {
    }

    public boolean init() throws IOException {
        File f = new File("disco.dat");
        boolean exists = f.exists();
        raf = new RandomAccessFile(f, "rws");
        if (!exists) {
            raf.setLength(NUM_BLOCOS * TAM_BLOCO);
        }
        return exists;

    }

    public byte[] read(int numBlloco) throws IOException {
        if(numBlloco > NUM_BLOCOS || numBlloco < 0 ) {
            throw new IllegalArgumentException("numBlloco should be between 0 and " + (NUM_BLOCOS-1));
        }
        raf.seek(numBlloco * TAM_BLOCO);
        byte[] read = new byte[TAM_BLOCO];
        raf.read(read);
        return read;
    }

    public void write(int numBlloco, byte[] data) throws IOException {
        if(numBlloco > NUM_BLOCOS || numBlloco < 0 ) {
            throw new IllegalArgumentException("numBlloco should be between 0 and " + (NUM_BLOCOS-1));

        }
        if(data == null || data.length == 0 || data.length > TAM_BLOCO) {
            throw new IllegalArgumentException("data length should be between 0 and " + (TAM_BLOCO-1));

        }
        raf.seek(numBlloco * TAM_BLOCO);
        raf.write(data);
    }




}
