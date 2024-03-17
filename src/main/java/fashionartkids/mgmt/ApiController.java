package fashionartkids.mgmt;

import fashionartkids.mgmt.model.talent.Talent;
import fashionartkids.mgmt.service.TalentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ApiController {

    @Autowired
    private TalentService talentService;

    @RequestMapping(value = "/models/fetch", method = RequestMethod.GET)
    public DataTablesOutput<Talent> list(DataTablesInput input) {
        return talentService.fetchAllNew(input);
    }
}

