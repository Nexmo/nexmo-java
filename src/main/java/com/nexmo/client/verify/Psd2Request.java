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

public class Psd2Request extends BaseRequest {

    private Double amount;
    private String payee;
    private Workflow workflow;

    /**
     * @param number The recipient's phone number in <a href="https://en.wikipedia.org/wiki/E.164">E.164</a>
     *               format.
     * @param amount The decimal amount of the payment to be confirmed, in Euros.
     * @param payee  An alphanumeric string to indicate to the user the name of the recipient that they
     *               are confirming a payment to.
     */
    public Psd2Request(String number, Double amount, String payee) {
        super(number,null, null);
        this.amount = amount;
        this.payee = payee;
    }

    /**
     * @param number The recipient's phone number in <a href="https://en.wikipedia.org/wiki/E.164">E.164</a>
     *               format.
     * @param amount The decimal amount of the payment to be confirmed, in Euros.
     * @param payee  An alphanumeric string to indicate to the user the name of the recipient that they
     *               are confirming a payment to.
     * @param workflow Selects the predefined sequence of SMS and TTS (Text To Speech) actions to use
     *                 in order to convey the PIN to your user. For example, an id of 1 identifies the
     *                 workflow SMS - TTS - TTS. For a list of all workflows and their associated ids,
     *                 please visit the
     *                 <a href="https://developer.nexmo.com/verify/guides/workflows-and-events">developer portal.</a>
     */
    public Psd2Request(String number, Double amount, String payee, Workflow workflow) {
        this(number, amount, payee);
        setWorkflow(workflow);
    }

    /**
     * @return The decimal amount of the payment to be confirmed, in Euros
     */
    public Double getAmount() {
        return amount;
    }

    /**
     * @return An alphanumeric string to indicate to the user the name of the recipient that they are confirming a payment to.
     */
    public String getPayee() {
        return this.payee;
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
        return "Psd2Request{" +
                super.toString() +
                "amount=" + amount +
                ", payee='" + payee + '\'' +
                ", workflow=" + workflow +
                ", local=" + getDashedLocale() +
                ", country=" + getCountry() +
                ", pinExpiry=" + getPinExpiry() +
                ", nextEventWait=" + getNextEventWait() +
                '}';
    }
}
