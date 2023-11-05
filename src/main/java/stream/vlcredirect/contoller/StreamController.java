package stream.vlcredirect.contoller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.annotation.RequestScope;

import java.util.Base64;

@Slf4j
@RestController
@RequestScope
public class StreamController {

    @RequestMapping(value = "/{device}/{base64}", method = RequestMethod.GET)
    public void redirect(@PathVariable("device") String device, @PathVariable("base64") String base64, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {

        String url = new String(Base64.getDecoder().decode(base64));

        log.info(device);
        log.info(url);

        switch (device) {
            case "android":
                httpServletResponse.setHeader("Location", "vlc://"+url);
                break;
            case "ios":
                httpServletResponse.setHeader("Location", "vlc-x-callback://x-callback-url/ACTION?url="+url);
                break;
        }

        httpServletResponse.setStatus(302);
    }

}
