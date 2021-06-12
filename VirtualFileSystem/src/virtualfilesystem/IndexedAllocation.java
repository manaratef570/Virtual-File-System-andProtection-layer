/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package virtualfilesystem;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Manar Atef
 */
public class IndexedAllocation implements Alocation_techniques {

    @Override
    public File Allocation(int[] disk , String name, int fileSize , int startFile,int arr[]){

        File New = null;

        int allocatedSize;
        System.out.println("enter block size");
        Scanner input = new Scanner(System.in);
        allocatedSize = input.nextInt();

        ArrayList<ArrayList<Integer>> indexBlock = new ArrayList<ArrayList<Integer>>();
        ArrayList<Integer> idxBlk = new ArrayList<>();
        indexBlock.add(idxBlk);
        int number = 0;
        int numberOfNewAllocationsInDisk = 0;
        for (int i = 0; i < disk.length ; i++) {
            if (numberOfNewAllocationsInDisk == fileSize)
                break;
            if (disk[i] == 0){
                if (indexBlock.get(number).size() != allocatedSize) {
                    indexBlock.get(number).add(i);
                    numberOfNewAllocationsInDisk++;
                }
                else {
                    ArrayList<Integer> AnotherBlock = new ArrayList<>();
                    indexBlock.add(AnotherBlock);
                    number++;
                    indexBlock.get(number).add(i);
                    numberOfNewAllocationsInDisk++;
                }
                disk[i] = 1;
            }
        }
        if (numberOfNewAllocationsInDisk < fileSize){
            System.out.println("Insufficient Space");
            for (int i = 0; i < indexBlock.size() ; i++) {
                for (int j = 0; j < allocatedSize ; j++) {
                    disk[indexBlock.get(i).get(j)] = 0;
                }
            }
        }else {
            New = new File();
            New.setIndexedBlocksForIndexedAllocation(indexBlock);
            New.setFilePath(name);
            New.setDeleted(false);


        }


       return New;


    }

    @Override
    public void DeAllocation(File file, int[] disk) {
        for(int i=0;i<file.getIndexedBlocksForIndexedAllocation().size();i++){
            for (int j = 0; j < file.getIndexedBlocksForIndexedAllocation().get(i).size() ; j++) {
                disk[file.getIndexedBlocksForIndexedAllocation().get(i).get(j)] = 0;
            }



        }
    }

    @Override
    public void writeIntoFile(Directory curr, String name, FileWriter myWriter) {


        try{



            myWriter.write(name+curr.getDirectoryPath()+'\n');

            name+=curr.getDirectoryPath();
            for(int i=0;i<curr.getFiles().size();i++){

                myWriter.write('-'+name+'/'+curr.getFiles().get(i).getFilePath()+"\n");

                // modifications for indexed allocation
                if (curr.getFiles().get(i).getIndexedBlocksForIndexedAllocation() == null){
                    for(int j=0;j<curr.getFiles().get(i).getAllocatedBlocks().length;j++){
                        myWriter.write(curr.getFiles().get(i).getAllocatedBlocks()[j]+" ");
                    }
                }else {
                    for(int j=0;j<curr.getFiles().get(i).getIndexedBlocksForIndexedAllocation().size();j++){
                        for (int k = 0; k < curr.getFiles().get(i).getIndexedBlocksForIndexedAllocation().get(j).size() ; k++) {
                            myWriter.write(curr.getFiles().get(i).getIndexedBlocksForIndexedAllocation().get(j).get(k)+" ");
                        }

                    }
                }



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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }



}
