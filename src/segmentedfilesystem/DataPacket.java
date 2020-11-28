package segmentedfilesystem;

import java.net.DatagramPacket;
import java.util.Arrays;

public class DataPacket {
    byte status;
    byte fileID;
    int packetNumber;
    byte[] bytes;
    byte[] skip;
    boolean isAdded;

    public DataPacket(DatagramPacket pack) {
        this.bytes = pack.getData();
        this.constructPacketNumber();
        this.fileID = bytes[1];
        this.status = bytes[0];
        int length = pack.getLength(); // gets length
        this.skip = Arrays.copyOfRange(pack.getData(), 4, length); // skips first 4
        this.isAdded = false;
    }

    public void constructPacketNumber() {
        int x = Byte.toUnsignedInt(bytes[2]); // don't have better names for
        int y = Byte.toUnsignedInt(bytes[3]); // x and y as instructed in README.md file

        this.packetNumber = (256 * x + y);
    }

    public byte getFileID() {
        return fileID;
    }
}
