import java.math.BigInteger;
import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class A {

    public static void main(String[] args) {
        // Input CIDR notation
        String cidrNotation = "2001:db8::/64"; // Replace with your CIDR notation
        try {
            String[] parts = cidrNotation.split("/");
            if (parts.length != 2) {
                System.out.println("Invalid IPv6 CIDR notation");
                return;
            }
            String ipAddress = parts[0];
            int prefixLength = Integer.parseInt(parts[1]);
            BigInteger[] headAndTail = calculateHeadAndTail(ipAddress, prefixLength);
        } catch (NumberFormatException | UnknownHostException e) {
            System.out.println("Invalid IPv6 CIDR notation");
        }
    }

    public static BigInteger[] calculateHeadAndTail(String ipAddress, int prefixLength) throws UnknownHostException {
        InetAddress inetAddress = InetAddress.getByName(ipAddress);
        byte[] addr = ((Inet6Address) inetAddress).getAddress();
        BigInteger head = new BigInteger(1, addr);
        BigInteger mask = BigInteger.ONE.shiftLeft(128 - prefixLength).subtract(BigInteger.ONE);
        BigInteger tail = head.or(mask);
        return new BigInteger[] { head, tail };
    }
}
