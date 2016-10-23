package ge.dt.service.predixapp.operation;

import ge.dt.service.predixapp.dto.EngineDto;
import ge.dt.service.predixapp.model.Engine;
import ge.dt.service.predixapp.repository.EngineRepository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EngineOperations {

	@Autowired
	private EngineRepository engineRepository;

	public List<Engine> getEngineDetails() {
		List<Engine> engineList = (List<Engine>) engineRepository.findAll();
		return engineList;
	}

	public void addEngines(List<EngineDto> engineDtoList) {
		List<Engine> engineList = new ArrayList<>();
		for (EngineDto engineDto : engineDtoList) {
			Engine engine = null;
			List<Engine> engineListFromDb = engineRepository
					.findBySerial(engineDto.getSerial());
			if (null != engineListFromDb && !engineListFromDb.isEmpty()) {
				engine = engineListFromDb.get(0);
				engine.setPart(engineDto.getPart());
				engine.setInvStatus(engineDto.getInvStatus());
				engine.setTopSerial(engineDto.getTopSerial());
				engine.setInstalledOn(engineDto.getInstalledOn());
				engine.setCycle(engineDto.getCycle());

			} else {
				engine = new Engine();
				BeanUtils.copyProperties(engineDto, engine);
			}

			engine.setCreatedDate(new Date());
			engineList.add(engine);
		}
		engineRepository.save(engineList);
	}

}
