//
// Diese Datei wurde mit der JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.3.0-b170531.0717 generiert 
// Siehe <a href="https://jaxb.java.net/">https://jaxb.java.net/</a> 
// Änderungen an dieser Datei gehen bei einer Neukompilierung des Quellschemas verloren. 
// Generiert: 2017.10.28 um 11:56:51 PM CEST 
//


package com.tutego.insel.xml.jaxb.bible;

import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java-Klasse für anonymous complex type.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * 
 * <pre>
 * &lt;complexType&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="title" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="range"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;sequence&gt;
 *                   &lt;element name="request" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *                   &lt;element name="result" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *                   &lt;element name="item"&gt;
 *                     &lt;complexType&gt;
 *                       &lt;complexContent&gt;
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                           &lt;sequence&gt;
 *                             &lt;element name="bookname" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *                             &lt;element name="chapter" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *                             &lt;element name="verse" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *                             &lt;element name="text" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *                           &lt;/sequence&gt;
 *                         &lt;/restriction&gt;
 *                       &lt;/complexContent&gt;
 *                     &lt;/complexType&gt;
 *                   &lt;/element&gt;
 *                 &lt;/sequence&gt;
 *               &lt;/restriction&gt;
 *             &lt;/complexContent&gt;
 *           &lt;/complexType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="time" type="{http://www.w3.org/2001/XMLSchema}decimal"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "title",
    "range",
    "time"
})
@XmlRootElement(name = "bible")
public class Bible {

    @XmlElement(required = true)
    protected String title;
    @XmlElement(required = true)
    protected Bible.Range range;
    @XmlElement(required = true)
    protected BigDecimal time;

    /**
     * Ruft den Wert der title-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTitle() {
        return title;
    }

    /**
     * Legt den Wert der title-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTitle(String value) {
        this.title = value;
    }

    /**
     * Ruft den Wert der range-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link Bible.Range }
     *     
     */
    public Bible.Range getRange() {
        return range;
    }

    /**
     * Legt den Wert der range-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link Bible.Range }
     *     
     */
    public void setRange(Bible.Range value) {
        this.range = value;
    }

    /**
     * Ruft den Wert der time-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getTime() {
        return time;
    }

    /**
     * Legt den Wert der time-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setTime(BigDecimal value) {
        this.time = value;
    }


    /**
     * <p>Java-Klasse für anonymous complex type.
     * 
     * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
     * 
     * <pre>
     * &lt;complexType&gt;
     *   &lt;complexContent&gt;
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
     *       &lt;sequence&gt;
     *         &lt;element name="request" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
     *         &lt;element name="result" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
     *         &lt;element name="item"&gt;
     *           &lt;complexType&gt;
     *             &lt;complexContent&gt;
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
     *                 &lt;sequence&gt;
     *                   &lt;element name="bookname" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
     *                   &lt;element name="chapter" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
     *                   &lt;element name="verse" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
     *                   &lt;element name="text" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
     *                 &lt;/sequence&gt;
     *               &lt;/restriction&gt;
     *             &lt;/complexContent&gt;
     *           &lt;/complexType&gt;
     *         &lt;/element&gt;
     *       &lt;/sequence&gt;
     *     &lt;/restriction&gt;
     *   &lt;/complexContent&gt;
     * &lt;/complexType&gt;
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "request",
        "result",
        "item"
    })
    public static class Range {

        @XmlElement(required = true)
        protected String request;
        @XmlElement(required = true)
        protected String result;
        @XmlElement(required = true)
        protected Bible.Range.Item item;

        /**
         * Ruft den Wert der request-Eigenschaft ab.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getRequest() {
            return request;
        }

        /**
         * Legt den Wert der request-Eigenschaft fest.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setRequest(String value) {
            this.request = value;
        }

        /**
         * Ruft den Wert der result-Eigenschaft ab.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getResult() {
            return result;
        }

        /**
         * Legt den Wert der result-Eigenschaft fest.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setResult(String value) {
            this.result = value;
        }

        /**
         * Ruft den Wert der item-Eigenschaft ab.
         * 
         * @return
         *     possible object is
         *     {@link Bible.Range.Item }
         *     
         */
        public Bible.Range.Item getItem() {
            return item;
        }

        /**
         * Legt den Wert der item-Eigenschaft fest.
         * 
         * @param value
         *     allowed object is
         *     {@link Bible.Range.Item }
         *     
         */
        public void setItem(Bible.Range.Item value) {
            this.item = value;
        }


        /**
         * <p>Java-Klasse für anonymous complex type.
         * 
         * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
         * 
         * <pre>
         * &lt;complexType&gt;
         *   &lt;complexContent&gt;
         *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
         *       &lt;sequence&gt;
         *         &lt;element name="bookname" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
         *         &lt;element name="chapter" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
         *         &lt;element name="verse" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
         *         &lt;element name="text" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
         *       &lt;/sequence&gt;
         *     &lt;/restriction&gt;
         *   &lt;/complexContent&gt;
         * &lt;/complexType&gt;
         * </pre>
         * 
         * 
         */
        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "", propOrder = {
            "bookname",
            "chapter",
            "verse",
            "text"
        })
        public static class Item {

            @XmlElement(required = true)
            protected String bookname;
            protected int chapter;
            protected int verse;
            @XmlElement(required = true)
            protected String text;

            /**
             * Ruft den Wert der bookname-Eigenschaft ab.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getBookname() {
                return bookname;
            }

            /**
             * Legt den Wert der bookname-Eigenschaft fest.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setBookname(String value) {
                this.bookname = value;
            }

            /**
             * Ruft den Wert der chapter-Eigenschaft ab.
             * 
             */
            public int getChapter() {
                return chapter;
            }

            /**
             * Legt den Wert der chapter-Eigenschaft fest.
             * 
             */
            public void setChapter(int value) {
                this.chapter = value;
            }

            /**
             * Ruft den Wert der verse-Eigenschaft ab.
             * 
             */
            public int getVerse() {
                return verse;
            }

            /**
             * Legt den Wert der verse-Eigenschaft fest.
             * 
             */
            public void setVerse(int value) {
                this.verse = value;
            }

            /**
             * Ruft den Wert der text-Eigenschaft ab.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getText() {
                return text;
            }

            /**
             * Legt den Wert der text-Eigenschaft fest.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setText(String value) {
                this.text = value;
            }

        }

    }

}
