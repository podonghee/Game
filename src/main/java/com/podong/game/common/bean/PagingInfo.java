package com.podong.game.common.bean;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;

@JsonIgnoreProperties(
        ignoreUnknown = true
)
public class PagingInfo implements Serializable {
    public long totalCount = 0L;
    public long totalElements = 0L;
    private int pageScale;
    private int BLOCK_SCALE = 10;
    private int currentPage;
    private int prevPage;
    private int nextPage;
    private int totalPages;
    private int totBlock;
    private int curBlock;
    private int prevBlock;
    private int nextBlock;
    private int pageBegin;
    private int pageEnd;
    private int blockBegin;
    private int blockEnd;

    public PagingInfo() {
    }
    public PagingInfo(long count, int currentPage,int pageScale) {
        this.curBlock = 1;
        this.currentPage = currentPage;
        this.totalElements = count;
        this.pageScale = pageScale;
        this.setTotalPages(count);
        this.setPageRange();
        this.setTotBlock();
        this.setBlockRange();
    }

    public void setBlockRange() {
        this.curBlock = (int)Math.ceil((double)((this.currentPage - 1) / this.BLOCK_SCALE)) + 1;
        this.blockBegin = (this.curBlock - 1) * this.BLOCK_SCALE + 1;
        this.blockEnd = this.blockBegin + this.BLOCK_SCALE - 1;
        if (this.blockEnd > this.totalPages) {
            this.blockEnd = this.totalPages;
        }

        this.prevPage = this.currentPage == 1 ? 1 : (this.curBlock - 1) * this.BLOCK_SCALE;
        this.nextPage = this.curBlock > this.totBlock ? this.curBlock * this.BLOCK_SCALE : this.curBlock * this.BLOCK_SCALE + 1;
        if (this.nextPage >= this.totalPages) {
            this.nextPage = this.totalPages;
        }

    }

    public void setPageRange() {
        this.pageBegin = this.currentPage * this.pageScale + 1;
        this.pageEnd = this.pageBegin + this.pageScale - 1;
    }

    public int getPageScale() {
        return pageScale;
    }

    public PagingInfo setPageScale(int pageScale) {
        this.pageScale = pageScale;
        return this;
    }

    public int getBLOCK_SCALE() {
        return this.BLOCK_SCALE;
    }

    public void setBLOCK_SCALE(int BLOCK_SCALE) {
        this.BLOCK_SCALE = BLOCK_SCALE;
    }

    public int getCurrentPage() {
        return this.currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getPrevPage() {
        return this.prevPage;
    }

    public void setPrevPage(int prevPage) {
        this.prevPage = prevPage;
    }

    public int getNextPage() {
        return this.nextPage;
    }

    public void setNextPage(int nextPage) {
        this.nextPage = nextPage;
    }

    public int getTotalPages() {
        return this.totalPages;
    }

    public void setTotalPages(long count) {
        this.totalPages = (int)Math.ceil((double)count * 1.0D / (double)this.pageScale);
    }

    public int getTotBlock() {
        return this.totBlock;
    }

    public void setTotBlock() {
        this.totBlock = (int)Math.ceil((double)(this.totalPages / this.BLOCK_SCALE));
    }

    public int getCurBlock() {
        return this.curBlock;
    }

    public void setCurBlock(int curBlock) {
        this.curBlock = curBlock;
    }

    public int getPrevBlock() {
        return this.prevBlock;
    }

    public void setPrevBlock(int prevBlock) {
        this.prevBlock = prevBlock;
    }

    public int getNextBlock() {
        return this.nextBlock;
    }

    public void setNextBlock(int nextBlock) {
        this.nextBlock = nextBlock;
    }

    public int getPageBegin() {
        return this.pageBegin;
    }

    public void setPageBegin(int pageBegin) {
        this.pageBegin = pageBegin;
    }

    public int getPageEnd() {
        return this.pageEnd;
    }

    public void setPageEnd(int pageEnd) {
        this.pageEnd = pageEnd;
    }

    public int getBlockBegin() {
        return this.blockBegin;
    }

    public void setBlockBegin(int blockBegin) {
        this.blockBegin = blockBegin;
    }

    public int getBlockEnd() {
        return this.blockEnd;
    }

    public void setBlockEnd(int blockEnd) {
        this.blockEnd = blockEnd;
    }

    public long getTotalCount() {
        return this.totalCount;
    }

    public void setTotalCount(long totalCount) {
        this.totalCount = totalCount;
        this.totalElements = totalCount;
    }
}
