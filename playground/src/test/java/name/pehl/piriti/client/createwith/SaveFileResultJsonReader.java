package name.pehl.piriti.client.createwith;

import name.pehl.piriti.commons.client.CreateWith;
import name.pehl.piriti.commons.client.Mapping;
import name.pehl.piriti.commons.client.Mappings;
import name.pehl.piriti.json.client.JsonReader;

@CreateWith(CustomInstanceCreator.class)
@Mappings({@Mapping("file"), @Mapping("message"), @Mapping("saved")})
public interface SaveFileResultJsonReader extends JsonReader<SaveFileResult>
{

}
