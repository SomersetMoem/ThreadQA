package model.swagger;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FullUser{

	@JsonProperty("pass")
	private String pass;

	@JsonProperty("games")
	private List<GamesItem> games;

	@JsonProperty("login")
	private String login;
}