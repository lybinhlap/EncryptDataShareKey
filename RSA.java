package sec.EncryptDataShareKey;

import java.math.BigInteger;
import java.security.SecureRandom;

public class RSA {
//d: private key, public key
    private BigInteger n, d, e;
//    public String text_d;
//    public String text_e;
//    public String text_n;

    public BigInteger getN() {
        return n;
    }

    public void setN(BigInteger n) {
        this.n = n;
    }

    public BigInteger getD() {
        return d;
    }

    public void setD(BigInteger d) {
        this.d = d;
    }

    public BigInteger getE() {
        return e;
    }

    public void setE(BigInteger e) {
        this.e = e;
    }

    /**
     * Create an instance that can encrypt using someone elses public key.
     */
    public RSA(BigInteger newn, BigInteger newe) {
        n = newn;
        e = newe;
    }

    /**
     * Create an instance that can both encrypt and decrypt.
     */
    public RSA() {
        
       
    }
    
    public void KeyRSA(int bits){

        SecureRandom r = new SecureRandom();//create BigInteger r random
        BigInteger p = new BigInteger(bits , 100, r);
        BigInteger q = new BigInteger(bits , 100, r);
        n = p.multiply(q);
        BigInteger m = (p.subtract(BigInteger.ONE)).multiply(q
                .subtract(BigInteger.ONE));
        boolean found = false;
        do {
            e = new BigInteger(bits , 50, r);
            if (m.gcd(e).equals(BigInteger.ONE) && e.compareTo(m) < 0) {
                found = true;
            }
        } while (!found);
        d = e.modInverse(m);
//        text_e = new String(BigInteger(e));
//        System.out.println("e: "+e);
//        System.out.println("d: "+d);
//        System.out.println("n: "+n);
    }


    // Encrypt the given plaintext message.Use public key encrypt
   
    
    public synchronized String encrypt(String message) {
        return (new BigInteger(message.getBytes())).modPow(e, n).toString();
    }

   
    //Encrypt the given plaintext message.Use public key encrypt
 
    public synchronized BigInteger encrypt(BigInteger message) {
        return message.modPow(e, n);
    }

  
     // Decrypt the given ciphertext message.Use private key decrypt
   
    public synchronized String decrypt(String message) {
        return new String((new BigInteger(message)).modPow(d, n).toByteArray());
    }

  
     // Decrypt the given ciphertext message.Use private key decrypt
 
    public synchronized BigInteger decrypt(BigInteger message) {
        return message.modPow(d, n);
    }

    void setN(int bitleg) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }    
}
