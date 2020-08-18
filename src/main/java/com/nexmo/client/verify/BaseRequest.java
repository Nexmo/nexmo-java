/*
 * Copyright (c) 2011-2017 Nexmo Inc
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package com.nexmo.client.verify;

import com.fasterxml.jackson.annotation.JsonValue;

import java.util.Locale;

/**
 * Base request class for {@link VerifyRequest} and {@link Psd2Request}
 * @since 5.5.0
 */
public class BaseRequest {

    private final String number;
    private String from;
    private Integer length;
    private Locale locale;
    private String country;
    private Integer pinExpiry;
    private Integer nextEventWait;

    public BaseRequest(String number, Integer length, Locale locale) {
        this.number = number;
        this.length = length;
        this.locale = locale;
        this.country = null;
        this.pinExpiry = null;
        this.nextEventWait = null;
    }

    /**
     * @return the recipient's phone number provided in the constructor, in
     * <a href="https://en.wikipedia.org/wiki/E.164">E.164</a> format.
     */
    public String getNumber() {
        return number;
    }

    /**
     * @return the length of the verification code to be sent to the user, specified in some {@link VerifyRequest}
     * constructors. {@code -1} indicates the default length will be used.
     */
    public Integer getLength() {
        return length;
    }

    public void setLength(Integer length) {
        this.length = length;
    }

    /**
     * @return the default locale used for verification. If this value is {@code null}, the locale will be determined
     * from the country code included in {@code number}
     */
    public Locale getLocale() {
        return locale;
    }


    public void setLocale(Locale locale) {
        this.locale = locale;
    }

    /**
     * @return the default locale used for verification in snake case.
     * Ex: {@code en-gb}
     * If this value is {@code null}, the locale will be determined
     * from the country code included in {@code number}
     */
    public String getDashedLocale(){
        if(locale != null){
           return locale.toLanguageTag().toLowerCase();
        }
        else return null;
    }

    /**
     * The country for the destination phone number.
     *
     * @return a String containing a 2-character country code
     */
    public String getCountry() {
        return country;
    }

    /**
     * The country for the destination phone number.
     * <p>
     * If you wish to used localised number formats or you are not sure if number is correctly formatted, set this to a
     * two-character country code. For example, GB, US. Verify will work out the international phone number for you.
     *
     * @param country a String containing a 2-character country code
     */
    public void setCountry(String country) {
        this.country = country;
    }

    /**
     * Get the PIN validity time from generation, in seconds, or null if this has not been set.
     *
     * @return PIN expiry time in seconds
     */
    public Integer getPinExpiry() {
        return pinExpiry;
    }

    /**
     * Set the PIN validity time from generation, in seconds. The default (null) is 300 seconds.
     *
     * @param pinExpiry PIN expiry time in seconds.
     */
    public void setPinExpiry(Integer pinExpiry) {
        this.pinExpiry = pinExpiry;
    }

    /**
     * Get the wait time between attempts to deliver the PIN.
     *
     * @return An Integer between 600-900, or null.
     */
    public Integer getNextEventWait() {
        return nextEventWait;
    }

    /**
     * Set the wait time between attempts to deliver the PIN.
     *
     * @param nextEventWait An Integer value between 60 and 900 seconds, or null to use the default duration.
     */
    public void setNextEventWait(Integer nextEventWait) {
        this.nextEventWait = nextEventWait;
    }

    @Override
    public String toString() {
        return  "number='" + number + '\'' +
                ", from='" + from + '\'' +
                ", length=" + length +
                ", locale=" + locale +
                ", country='" + country + '\'' +
                ", pinExpiry=" + pinExpiry +
                ", nextEventWait=" + nextEventWait;
    }
}
