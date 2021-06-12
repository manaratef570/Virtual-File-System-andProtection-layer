/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package virtualfilesystem;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author Manar Atef
 */
public class  ContiguousAllocation implements Alocation_techniques{
    private int start;
    private int length;
   
   public File Allocation(int disk[],String name,int size,int startFile,int allocated[]){
        
       File New=null;
       int arr[]= new int[size];
       if(startFile==-1){
       int countZero=0;
       
        for(int i=0;i<disk.length;i++){
        if(disk[i]==0){
        countZero++;
        } 
        }
        
        if(size<=countZero){
        for(int i=0;i<disk.length;i++)  {
           
           if(disk[i]==0){
           start=i;
           break;
           }
       
       }
       
       length =size;
       
       
       int count=0;
     for(int i=start;i<(start+length);i++){  
         arr[count++]=i;
         disk[i]=1;
   }
        }
       }
       
       else{
           int count=0;
       for(int i=startFile;i<size+startFile;i++){
            disk[i]=1;
            arr[count++]=i;
       
       }
       
       
       }  
       
    New= new File();
    New.setFilePath(name);
    New.setAllocatedBlocks(arr);
    New.setDeleted(false);
        
        
        
   return New;
   }

   public  void DeAllocation(File file,int disk[]){

   for(int i=0;i<file.getAllocatedBlocks().length;i++){
        
        disk[file.getAllocatedBlocks()[i]]=0;
        
   
   }
   
}

    @Override
    public void writeIntoFile(Directory curr,String name,FileWriter myWriter) {
        
        try {
            

        
            myWriter.write(name+curr.getDirectoryPath()+'\n');
        
            name+=curr.getDirectoryPath();
            for(int i=0;i<curr.getFiles().size();i++){
            
                   myWriter.write('-'+name+'/'+curr.getFiles().get(i).getFilePath()+" ");
                
                    myWriter.write(curr.getFiles().get(i).getAllocatedBlocks()[0]+"  "+curr.getFiles().get(i).getAllocatedBlocks().length);
                     
                
                   myWriter.write("\n");
            
            }
        
            
            for(int i=0;i<curr.getSubDirectories().size();i++){
   
                writeIntoFile(curr.getSubDirectories().get(i) , name+'/', myWriter);
   
            
 
                        
            }
               } catch (IOException ex) {
                   
               }

            


    }

    @Override
    public void loadFromFile(Directory root, FileReader myFile,int disk[]) {
       
        Manger mange= new Manger();
        Scanner file = new Scanner(myFile);

        String newOne = null;
       if(file.hasNext()){
        file.next();
       }
       
        while(file.hasNext()){
            
        String read=file.next();
        
        if(read.charAt(0)=='-'){
        newOne=read.substring(1, read.length());
        int startFile=file.nextInt();
        int size=file.nextInt();
        mange.createFile(root, newOne,size,this,disk,startFile,null);
     
        }
     
        else{
        
        mange.creatFolder(root, read);
        }
        }

    }
    
    
    
    
   
}