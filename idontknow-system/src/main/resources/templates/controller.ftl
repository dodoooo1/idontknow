
import com.idontknow.business.adapter.base.BaseSystemController;
import com.idontknow.business.constants.AppUrls;
import com.idontknow.business.shared.ApiListPaginationSuccess;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping(${classInfo.className}sController.BASE_URL)
@RequiredArgsConstructor
@Getter
public class ${classInfo.className}sController extends BaseSystemController<
${classInfo.className},
Create${classInfo.className}sRequest,
Update${classInfo.className}sRequest,
${classInfo.className}sResponse> {
public static final String BASE_URL = AppUrls.ROLE;
private final ${classInfo.className}sService service;
private final ${classInfo.className}sMapper mapper;

@GetMapping
public ApiListPaginationSuccess<${classInfo.className}sResponse> getRoles(@RequestBody Search${classInfo.className}sRequest request) {
    return service.get${classInfo.className}s(request);
    }
    }
