package eci.edu.byteProgramming.ejercicio.paper.util;

public class CryptoPayment extends PaymentMethod {
    private final String walletAddress;
    private final String cryptoType;
    private String blockchainHash;

    public CryptoPayment(double amount, String customerId, String description,
                         String walletAddress, String cryptoType) {
        super(amount, customerId, description);
        this.walletAddress = walletAddress;
        this.cryptoType = cryptoType;
    }

    @Override
    public String getPaymentMethod() {
        return "CRYPTOCURRENCY";
    }

    @Override
    protected boolean authorizePayment() {
        this.blockchainHash = generateBlockchainHash();
        System.out.println("Transaction broadcasted to blockchain");
        System.out.println("Blockchain hash: " + blockchainHash);
        return true;
    }

    private String generateBlockchainHash() {
        return "0x" + Integer.toHexString((int)(Math.random() * Integer.MAX_VALUE));
    }

    public String getWalletAddress() {
        return walletAddress;
    }

    public String getCryptoType() {
        return cryptoType;
    }

    public String getBlockchainHash() {
        return blockchainHash;
    }
}
