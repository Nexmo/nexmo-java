package com.nexmo.client.verify;

import com.nexmo.client.ClientTest;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class VerifyClientPsd2EndpointTest extends ClientTest<VerifyClient> {

    @Before
    public void setUp() {
        client = new VerifyClient(wrapper);
    }

    @Test
    public void testPsd2Verify() throws Exception {
        wrapper.setHttpClient(this.stubHttpClient(200,
                "{" + "\"request_id\": \"abcdef0123456789abcdef0123456789\"," + " \"status\": 0" + "}"
        ));

        VerifyResponse response = client.psd2Verify("447700900999", 10.31, "Ebony");

        assertEquals(VerifyStatus.OK, response.getStatus());
        assertEquals("abcdef0123456789abcdef0123456789", response.getRequestId());
    }

    @Test
    public void testPsd2VerifyWithWorkflow() throws Exception {
        wrapper.setHttpClient(this.stubHttpClient(200,
                "{" + "\"request_id\": \"abcdef0123456789abcdef0123456789\"," + " \"status\": 0" + "}"
        ));

        VerifyResponse response = client.psd2Verify("447700900999", 10.31, "Ebony", Psd2Request.Workflow.SMS);

        assertEquals(VerifyStatus.OK, response.getStatus());
        assertEquals("abcdef0123456789abcdef0123456789", response.getRequestId());
    }

    @Test
    public void testPsd2VerifyWithOptionalParameters() throws Exception {
        wrapper.setHttpClient(this.stubHttpClient(200,
                "{" + "\"request_id\": \"abcdef0123456789abcdef0123456789\"," + " \"status\": 0" + "}"
        ));

        Psd2Request request = new Psd2Request("447700900999", 10.31, "Ebony", Psd2Request.Workflow.SMS);
        System.out.println(request);
        request.setLength(4);

        VerifyResponse response = client.psd2Verify(request);

        assertEquals(VerifyStatus.OK, response.getStatus());
        assertEquals("abcdef0123456789abcdef0123456789", response.getRequestId());
    }
}
