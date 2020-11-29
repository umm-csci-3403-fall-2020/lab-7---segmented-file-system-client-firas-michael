package segmentedfilesystem;

import java.net.DatagramPacket;

import java.util.Arrays;

public class HeaderPacket {

    // byte[] pack;
    byte fileID;
    byte[] filename;

    DatagramPacket pack;

    public HeaderPacket(DatagramPacket pack) {

        this.pack = pack;

        fileID = pack.getData()[1];

        filename = Arrays.copyOfRange(pack.getData(), 2, pack.getLength());
    }

    public byte getFileID() {
        return this.fileID;
    }

    public byte[] getFileName() {
        return this.filename;
    }
}