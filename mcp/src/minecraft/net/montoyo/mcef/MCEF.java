package net.montoyo.mcef;

import net.montoyo.mcef.client.ClientProxy;
import net.montoyo.mcef.utilities.Log;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManagerFactory;
import java.security.KeyStore;
import java.security.SecureRandom;
import java.security.cert.Certificate;
import java.security.cert.CertificateFactory;

public class MCEF {
    
    public static final String VERSION = "1.11";
    public static boolean ENABLE_EXAMPLE;
    public static boolean SKIP_UPDATES;
    public static boolean WARN_UPDATES;
    public static boolean USE_FORGE_SPLASH;
    public static String FORCE_MIRROR = null;
    public static String HOME_PAGE;
    public static String[] CEF_ARGS = new String[0];
    public static boolean CHECK_VRAM_LEAK;
    public static SSLSocketFactory SSL_SOCKET_FACTORY;
    public static boolean SHUTDOWN_JCEF;
    public static boolean SECURE_MIRRORS_ONLY;
    
    //public static BaseProxy PROXY = new BaseProxy();
    public static ClientProxy PROXY_CLIENT = new ClientProxy();
    
    public static void init() {
    	onPreInit();
    	onInit();
    }
    
    private static void onPreInit() {

        //Config: main
        SKIP_UPDATES        = false;
        WARN_UPDATES        = true;
        USE_FORGE_SPLASH    = true;
        CEF_ARGS            = new String[]{"--disable-gpu"};
        SHUTDOWN_JCEF       = false;
        SECURE_MIRRORS_ONLY = true;

        //Config: exampleBrowser
        ENABLE_EXAMPLE = true;
        HOME_PAGE      = "mod://mcef/home.html";

        //Config: debug
        CHECK_VRAM_LEAK = false;

        importLetsEncryptCertificate();
        //PROXY.onPreInit();
        PROXY_CLIENT.onPreInit();
    }
    
    private static void onInit() {
        Log.info("Now initializing MCEF v%s...", VERSION);
        //PROXY.onInit();
        PROXY_CLIENT.onInit();
    }

    //Called by Minecraft.run() if the ShutdownPatcher succeeded
    public static void onMinecraftShutdown() {
        Log.info("Minecraft shutdown hook called!");
        //PROXY.onShutdown();
        PROXY_CLIENT.onShutdown();
    }

    //This is needed, otherwise for some reason HTTPS doesn't work
    private static void importLetsEncryptCertificate() {
        try {
            CertificateFactory cf = CertificateFactory.getInstance("X.509");
            Certificate cert = cf.generateCertificate(MCEF.class.getResourceAsStream("/assets/mcef/r3.crt"));

            KeyStore ks = KeyStore.getInstance(KeyStore.getDefaultType());
            ks.load(null, null);
            ks.setCertificateEntry("r3", cert);

            TrustManagerFactory tmf = TrustManagerFactory.getInstance("X509");
            tmf.init(ks);

            SSLContext sslCtx = SSLContext.getInstance("TLS");
            sslCtx.init(null, tmf.getTrustManagers(), new SecureRandom());

            SSL_SOCKET_FACTORY = sslCtx.getSocketFactory();
            Log.info("Successfully loaded Let's Encrypt certificate");
        } catch(Throwable t) {
            Log.error("Could not import Let's Encrypt certificate!! HTTPS downloads WILL fail...");
            t.printStackTrace();
        }
    }

}
