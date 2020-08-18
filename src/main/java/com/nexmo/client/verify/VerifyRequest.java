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
 * Describes a Verify request when passed to {@link com.nexmo.client.verify.VerifyEndpoint})}.
 */
public class VerifyRequest extends BaseRequest {
    private LineType type;
    private String brand;
    private String from;
    private Workflow workflow;



    /**
     * Constructor.
     *
     * @param number (required) The recipient's phone number in <a href="https://en.wikipedia.org/wiki/E.164">E.164</a>
     *               format.
     * @param brand  (required) The name of the company or app to be verified for. Must not be longer than 18
     *               characters.
     */
    public VerifyRequest(final String number, final String brand) {
        this(number, brand, null, -1, null, null);
    }

    /**
     * Constructor.
     *
     * @param number (required) The recipient's phone number in <a href="https://en.wikipedia.org/wiki/E.164">E.164</a>
     *               format.
     * @param brand  (required) The name of the company or app you are verifying for. Must not be longer than 18
     *               characters.
     * @param from   (optional The Nexmo number to use as the sender for the verification SMS message and calls, in
     *               <a href="https://en.wikipedia.org/wiki/E.164">E.164</a> format.
     */
    public VerifyRequest(final String number, final String brand, final String from) {
        this(number, brand, from, -1, null, null);
    }

    /**
     * Constructor.
     *
     * @param number (required) The recipient's phone number in <a href="https://en.wikipedia.org/wiki/E.164">E.164</a>
     *               format.
     * @param brand  (required) The name of the company or app you are verifying for. Must not be longer than 18
     *               characters.
     * @param from   (optional The Nexmo number to use as the sender for the verification SMS message and calls, in
     *               <a href="https://en.wikipedia.org/wiki/E.164">E.164</a> format.
     * @param length (optional) The length of the verification code to be sent to the user. Must be either 4 or 6. Use
     *               -1 to use the default value.
     * @param locale (optional) Override the default locale used for verification. By default the locale is determined
     *               from the country code included in {@code number}
     */
    public VerifyRequest(final String number, final String brand, final String from, final int length, final Locale locale) {
        this(number, brand, from, length, locale, null);
    }

    /**
     * Constructor.
     *
     * @param number (required) The recipient's phone number in <a href="https://en.wikipedia.org/wiki/E.164">E.164</a>
     *               format.
     * @param brand  (required) The name of the company or app you are verifying for. Must not be longer than 18
     *               characters.
     * @param from   (optional A short alphanumeric string to specify the SenderID for SMS sent by Verify. Depending on
     *               the destination of the phone number you are applying, restrictions may apply. By default, sender_id
     *               is {@code VERIFY}. Must be 11 characters or fewer.
     * @param length (optional) The length of the verification code to be sent to the user. Must be either 4 or 6. Use
     *               -1 to use the default value.
     * @param locale (optional) Override the default locale used for verification. By default the locale is determined
     *               from the country code included in {@code number}
     * @param type   (optional) If provided, restrict the verification to the specified network type. Contact
     *               support@nexmo.com to enable this feature.
     */
    public VerifyRequest(final String number, final String brand, final String from, final int length, final Locale locale, final LineType type) {
        super(number, length, locale);

        this.type = type;
        this.brand = brand;
        this.from = from;
        this.workflow = null;
        setCountry(null);
        setPinExpiry(null);
        setNextEventWait(null);
    }

    /**
     * @return the name of the company or app to be verified for.
     */
    public String getBrand() {
        return this.brand;
    }


    /**
     * @return the type of network the verification will be restricted to. This value has no effect unless it has been
     * enabled by contacting {@code support@nexmo.com}.
     */
    public LineType getType() {
        return type;
    }


    public void setType(LineType type) {
        this.type = type;
    }


    /**
     * @return the short alphanumeric string to specify the SenderID for SMS sent by Verify, or {@code null} if one was
     * not provided. This value is specified in some {@link BaseRequest}  sub-class constructors.
     * <p>
     * If this value is {@code null</tt>, the sender_id used will be <tt>VERIFY}.
     */
    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    /**
     * Types of phone line to be specified for {@link VerifyRequest#type}. This option is not generally available. It will
     * cause an error to be returned if your account doesn't have access to use this option.
     */
    public enum LineType {
        ALL, MOBILE, LANDLINE,
    }

    /**
     * @return The predefined sequence of SMS and TTS (Text To Speech) actions to use in order to convey the PIN to your
     * user.
     */
    public Workflow getWorkflow() {
        return workflow;
    }

    /**
     * Set the predefined sequence of SMS and TTS (Text To Speech) actions to use in order to convey the PIN to your
     * user. See https://developer.nexmo.com/verify/guides/workflows-and-events
     *
     * @param workflow The workflow to use for conveying the PIN to your user.
     */
    public void setWorkflow(Workflow workflow) {
        this.workflow = workflow;
    }

    /**
     * Enumeration representing different verification workflows.
     * <p>
     * See: https://developer.nexmo.com/verify/guides/workflows-and-events for more details.
     */
    public enum Workflow {
        /**
         * The default workflow.
         */
        SMS_TTS_TTS(1),
        SMS_SMS_TTS(2),
        TTS_TTS(3),
        SMS_SMS(4),
        SMS_TTS(5),
        SMS(6),
        TTS(7);

        private final int id;

        Workflow(int id) {
            this.id = id;
        }

        @JsonValue
        public int getId() {
            return id;
        }
    }

    @Override
    public String toString() {
        return "VerifyRequest{" +
                super.toString() +
                "type=" + type +
                ", brand='" + brand + '\'' +
                ", workflow=" + workflow +
                '}';
    }
}
