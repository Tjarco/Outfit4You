/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author Bono
 * 
 * @version 1.0
 * 
 * Klasse om paginatie te maken
 */
public class Paginatie {
    private final int aantalPerPagina = 9;
    private int aantalProducten = 0;
    private int aantalPaginas;
    private int currentPage;
    private int offset;
    
    public Paginatie(int currentPage)
    {
        this.currentPage = currentPage;
    }

    /**
     * @return the aantalProducten
     */
    public int getAantalProducten() 
    {
        return aantalProducten;
    }

    /**
     * @param aantalProducten the aantalProducten to set
     */
    public void setAantalProducten(int aantalProducten) 
    {
        this.aantalProducten = aantalProducten;
    }

    /**
     * @return the aantalPaginas
     */
    public int getAantalPaginas() 
    {
        return aantalPaginas;
    }

    /**
     * @param aantalPaginas the aantalPaginas to set
     */
    public void setAantalPaginas(int aantalPaginas) 
    {
        this.aantalPaginas = aantalPaginas;
    }

    /**
     * @return the currentPage
     */
    public int getCurrentPage() 
    {
        return currentPage;
    }

    /**
     * @param currentPage the currentPage to set
     */
    public void setCurrentPage(int currentPage) 
    {
        this.currentPage = currentPage;
    }
    
    /**
     * @return the aantalPerPagina
     */
    public int getAantalPerPagina()
    {
        return this.aantalPerPagina;
    }
    
    /**
     * Calculate and set the offset
     */
    public void calculateOffset()
    {
        this.offset = (this.currentPage - 1) * this.aantalPerPagina;
    }
    
    /**
     * @return the offset
     */
    public int getOffset()
    {
        return this.offset;
    }
    
    /**
     * Calculate and set the amount of pages
     */
    public void calculatePages()
    {
        this.aantalPaginas = (int) Math.ceil(this.aantalProducten / (this.aantalPerPagina * 1.0)); //*1.0 zodat het een double word; Anders vind er integer calculatie plaats en dat moet niet.
    }
    
    /**
     * Increments the currentPage
     */
    public void nextPage()
    {
        this.currentPage++;
    }
    
    /**
     * Decrements the currentPage
     */
    public void previousPage()
    {
        this.currentPage--;
    }
}
