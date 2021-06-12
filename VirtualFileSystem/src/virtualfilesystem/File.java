/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package virtualfilesystem;

import java.util.ArrayList;

/**
 *
 * @author zyad
 */
public class File {

    private String filePath;
    private int[] allocatedBlocks;
    private ArrayList<ArrayList<Integer>> indexedBlocksForIndexedAllocation = null;
    private boolean deleted;


    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public int[] getAllocatedBlocks() {
        return allocatedBlocks;
    }

    public void setAllocatedBlocks(int[] allocatedBlocks) {
        this.allocatedBlocks = allocatedBlocks;
    }

    public ArrayList<ArrayList<Integer>> getIndexedBlocksForIndexedAllocation() {
        return indexedBlocksForIndexedAllocation;
    }

    public void setIndexedBlocksForIndexedAllocation(ArrayList<ArrayList<Integer>> indexedBlocksForIndexedAllocation) {
        this.indexedBlocksForIndexedAllocation = indexedBlocksForIndexedAllocation;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }




}
