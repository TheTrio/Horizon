package sample;

import javax.swing.*;
import java.io.IOException;
import java.nio.file.*;
import java.util.List;

import static java.nio.file.StandardWatchEventKinds.*;
public class FeelChange {


    /*public void makeChange(){
        Path myDir  = Paths.get("");

        try{
            WatchService watcher = myDir.getFileSystem().newWatchService();

            myDir.register(watcher, ENTRY_CREATE, ENTRY_DELETE, ENTRY_MODIFY);

            WatchKey watchkey = watcher.take();

            List<WatchEvent<?>> events = watchkey.pollEvents();

            for(WatchEvent event : events){
                if(event.kind() == ENTRY_CREATE)
                    System.out.println("Created");
                if(event.kind()==ENTRY_DELETE){
                    System.out.println("Deleted");
                }
                if(event.kind() ==ENTRY_MODIFY)
                    System.out.println("Modified");
            }
        }catch (Exception e){

        }
    }
    */

    public void makeChange(String s){
        final Path path = Paths.get("");
        System.out.println(path);
        try (final WatchService watchService = FileSystems.getDefault().newWatchService()) {
            final WatchKey watchKey = path.register(watchService, StandardWatchEventKinds.ENTRY_MODIFY);
            while (true) {
                final WatchKey wk = watchService.take();
                for (WatchEvent<?> event : wk.pollEvents()) {
                    //we only register "ENTRY_MODIFY" so the context is always a Path.
                    final Path changed = (Path) event.context();
                    if (changed.endsWith(s + ".txt")) {
                        JOptionPane.showMessageDialog(null, "You have a new Message!");
                    }
                }
                // reset the key
                boolean valid = wk.reset();
                if (!valid) {
                    System.out.println("Key has been unregisterede");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
