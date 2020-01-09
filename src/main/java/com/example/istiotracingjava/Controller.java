package com.example.istiotracingjava;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @author eks5115
 */
@Slf4j
@RestController
public class Controller {

    private final RestTemplate template = new RestTemplate();

    @GetMapping("/")
    public String health() {
        return "";
    }

    @GetMapping("/a")
    public String a(@RequestHeader(value = "x-request-id", required = false) String xreq,
                    @RequestHeader(value = "x-b3-traceid", required = false) String xtraceid,
                    @RequestHeader(value = "x-b3-spanid", required = false) String xspanid,
                    @RequestHeader(value = "x-b3-parentspanid", required = false) String xparentspanid,
                    @RequestHeader(value = "x-b3-sampled", required = false) String xsampled,
                    @RequestHeader(value = "x-b3-flags", required = false) String xflags,
                    @RequestHeader(value = "x-ot-span-context", required = false) String xotspan,
                    @RequestHeader HttpHeaders headers) {
        log("a", xreq, xtraceid, xspanid, xparentspanid, xsampled, xflags, xotspan);
        String result = "a > ";

        HttpEntity<String> entity = getEntity(xreq, xtraceid, xspanid, xparentspanid, xsampled, xflags, xotspan);

        ResponseEntity<String> bEntity = template.exchange("http://istio-tracing-java-b/b", HttpMethod.GET, entity, String.class);
        result += bEntity.getBody();

        ResponseEntity<String> cEntity = template.exchange("http://istio-tracing-java-c/c", HttpMethod.GET, entity, String.class);
        result += cEntity.getBody();
        return result;
    }

    @GetMapping("/b")
    public String b(@RequestHeader(value = "x-request-id", required = false) String xreq,
                    @RequestHeader(value = "x-b3-traceid", required = false) String xtraceid,
                    @RequestHeader(value = "x-b3-spanid", required = false) String xspanid,
                    @RequestHeader(value = "x-b3-parentspanid", required = false) String xparentspanid,
                    @RequestHeader(value = "x-b3-sampled", required = false) String xsampled,
                    @RequestHeader(value = "x-b3-flags", required = false) String xflags,
                    @RequestHeader(value = "x-ot-span-context", required = false) String xotspan) {
        log("b", xreq, xtraceid, xspanid, xparentspanid, xsampled, xflags, xotspan);
        return "b; > ";
    }

    @GetMapping("/c")
    public String c(@RequestHeader(value = "x-request-id", required = false) String xreq,
                    @RequestHeader(value = "x-b3-traceid", required = false) String xtraceid,
                    @RequestHeader(value = "x-b3-spanid", required = false) String xspanid,
                    @RequestHeader(value = "x-b3-parentspanid", required = false) String xparentspanid,
                    @RequestHeader(value = "x-b3-sampled", required = false) String xsampled,
                    @RequestHeader(value = "x-b3-flags", required = false) String xflags,
                    @RequestHeader(value = "x-ot-span-context", required = false) String xotspan) {
        log("c", xreq, xtraceid, xspanid, xparentspanid, xsampled, xflags, xotspan);
        String result = "c > ";
        HttpEntity<String> entity = getEntity(xreq, xtraceid, xspanid, xparentspanid, xsampled, xflags, xotspan);
        ResponseEntity<String> dEntity = template.exchange("http://istio-tracing-java-d/d", HttpMethod.GET, entity, String.class);
        result += dEntity.getBody();

        ResponseEntity<String> eEntity = template.exchange("http://istio-tracing-java-e/e", HttpMethod.GET, entity, String.class);
        result += eEntity.getBody();
        return result;
    }

    @GetMapping("/d")
    public String d(@RequestHeader(value = "x-request-id", required = false) String xreq,
                    @RequestHeader(value = "x-b3-traceid", required = false) String xtraceid,
                    @RequestHeader(value = "x-b3-spanid", required = false) String xspanid,
                    @RequestHeader(value = "x-b3-parentspanid", required = false) String xparentspanid,
                    @RequestHeader(value = "x-b3-sampled", required = false) String xsampled,
                    @RequestHeader(value = "x-b3-flags", required = false) String xflags,
                    @RequestHeader(value = "x-ot-span-context", required = false) String xotspan) {
        log("d", xreq, xtraceid, xspanid, xparentspanid, xsampled, xflags, xotspan);
        return "d; > ";
    }

    @GetMapping("/e")
    public String e(@RequestHeader(value = "x-request-id", required = false) String xreq,
                    @RequestHeader(value = "x-b3-traceid", required = false) String xtraceid,
                    @RequestHeader(value = "x-b3-spanid", required = false) String xspanid,
                    @RequestHeader(value = "x-b3-parentspanid", required = false) String xparentspanid,
                    @RequestHeader(value = "x-b3-sampled", required = false) String xsampled,
                    @RequestHeader(value = "x-b3-flags", required = false) String xflags,
                    @RequestHeader(value = "x-ot-span-context", required = false) String xotspan) {
        log("e", xreq, xtraceid, xspanid, xparentspanid, xsampled, xflags, xotspan);
        return "e; > ";
    }

    private void log(String path, String xreq, String xtraceid, String xspanid, String xparentspanid,
                     String xsampled, String xflags, String xotspan) {
        log.info("path:{} -- x-request-id: {}, x-b3-traceid:{}, x-b3-spanid:{}, x-b3-parentspanid:{}, " +
                        "x-b3-sampled:{}, x-b3-flags:{}, x-ot-span-context:{}",
                path, xreq, xtraceid, xspanid, xparentspanid, xsampled, xflags, xotspan);
    }

    private HttpEntity<String> getEntity(String xreq, String xtraceid, String xspanid, String xparentspanid,
            String xsampled, String xflags, String xotspan) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("x-request-id", xreq);
        headers.add("x-b3-traceid", xtraceid);
        headers.add("x-b3-spanid", xspanid);
        headers.add("x-b3-parentspanid", xparentspanid);
        headers.add("x-b3-sampled", xsampled);
        headers.add("x-b3-flags", xflags);
        headers.add("x-ot-span-context", xotspan);

        return  new HttpEntity<>(null, headers);
    }
}
