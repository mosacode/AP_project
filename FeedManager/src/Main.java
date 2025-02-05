import DataBase.PineConeDataBase;
import DataTypes.Image;
import EmbeddingTools.EmbeddingService;
import EmbeddingTools.EmbeddingToolsClass;
import Server.Server;
import UserProfile.UserProfile;
import VendorTypes.*;
import io.pinecone.clients.Pinecone;
import org.openapitools.db_control.client.model.DeletionProtection;

import java.awt.*;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;


public class Main {
    static DataPool pool = DataPool.getInstance();

    public static boolean testRawTextOrg() {
        int tests = 3;
        int success = 0;

        System.out.printf("---------------------------------------------------------\nTesting RawTextORG:\n");

        System.out.printf("building...\n");
        RawTextOrg wef = new RawTextOrg("https://opencv.org/robots.txt");
        System.out.printf("Success\ngetText...\n");
        success++;
        {
            if (wef.getText().equals("# Termly scanner\n" +
                    "User-agent: TermlyBot\n" +
                    "Allow: /\n" +
                    "\n" +
                    "User-agent: *\n" +
                    "Disallow: /wp-login.php\n" +
                    "Disallow: /wp-admin/\n" +
                    "Crawl-delay: 600\n")) {
                System.out.printf("Success\n");
                success++;
            } else {
                System.out.printf("Failed!\n");
            }
        }
        System.out.printf("getURL...\n");
        System.out.printf(wef.getURL().toString() + "\n");
        success++;
        if (success < tests)
            System.out.printf("testRawTextOrg failed!\n");
        System.out.printf("\ntests: %d\nSuccessful: %d\n---------------------------------------------------------\n", tests, success);

        return success >= tests;
    }

    public static boolean testEmbeddingService(EmbeddingToolsClass embedder) {
        int tests = 1;
        int success = 0;

        System.out.printf("---------------------------------------------------------\nTesting EmbeddingService on "+embedder.getClass()+":\n");

        EmbeddingService embeddingService = new EmbeddingService(embedder);

        System.out.printf("getEmbedding...\n");
        ArrayList<Float> embedding =
                embeddingService.getEmbedding("Now we are ready to sail for the Horn " +
                "Weigh hey, roll and go! " +
                "Our boots and our clothes, boys, are all in the pawn " +
                "To be rollicking randy dandy-oh!");
        if(embedding != null) {
            System.out.printf("embedding of %d dimensions.\n", embedding.size());
            System.out.printf("Success\n");
            success++;
        }else
            System.out.printf("Fail\n");
        System.out.printf("\ntests: %d\nSuccessful: %d\n---------------------------------------------------------\n", tests, success);
        return success >= tests;
    }

    public static void testImageGet(){
        // local
        try{
            Image few = (new FullSupportVendor()).getImageLocal();
            Path tempFile = Files.createTempFile("tempImage", ".jpg");
            Files.write(tempFile, few.getImageData(), StandardOpenOption.WRITE);
            File fileToOpen = tempFile.toFile();
            Desktop.getDesktop().open(fileToOpen);
        }catch (Exception e){
            System.err.printf(e + "\n");
        }

        /* online
        try{

        }catch(Exception e){

        }*/
    }

    public static void main(String[] args){
        //testRawTextOrg();
        //testEmbeddingService(OllamaEmbeddingTool.getInstance("http://localhost:11434/api/embeddings"));
        //testImageGet();

        Server server = new Server();
        UserProfile user = new UserProfile("john_temple_bell", "nothing_is_obvious");
        server.registerUser(user);
        PineConeDataBase db = new PineConeDataBase();
        db.connect(server, user);
        System.out.printf(((Boolean)db.isConnected()).toString());
        db.createTable("indeX", 768);

    }
}
