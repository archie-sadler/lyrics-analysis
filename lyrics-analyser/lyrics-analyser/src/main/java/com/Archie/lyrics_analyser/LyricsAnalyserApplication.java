package com.Archie.lyrics_analyser;

import se.michaelthelin.spotify.SpotifyApi;
import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;
import se.michaelthelin.spotify.model_objects.credentials.AuthorizationCodeCredentials;
import se.michaelthelin.spotify.requests.authorization.authorization_code.AuthorizationCodeRefreshRequest;
import org.apache.hc.core5.http.ParseException;

import java.io.IOException;
import java.util.concurrent.CancellationException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;

//@SpringBootApplication
public class LyricsAnalyserApplication {

	private String spotifyClientID = "258dc3f70f314c3795a935f4780d79c7";
	private String spotifyClientSecret = "a3a21445543f4b33ba274fb7c83f0dab";
	private String authToken;
	private static URI redirectUri = SpotifyHttpManager.makeUri("https://google.com");
	private final code = "";


	private static final SpotifyApi spotifyAPI = new SpotifyApiBuilder()
			.setClientID(spotifyClientID)
			.setClientSecret(spotifyClientSecret)
			.setRedirectUri(redirectUri)
			.build();
	private static final AuthorizationCodeRefreshRequest authorizationCodeRefreshRequest = spotifyApi.authorizationCodeRefresh()
			.build();

	public static void authCodeRefreshSync(){

		try{
			final AuthorizationCodeCredentials authorizationCodeCredentials = authorizationCodeRefreshRequest.execute();

			spotifyApi.setAccessToken(AuthorizationCodeCredentials.getAccessToken());

			System.out.println("Expires in ..." + authorizationCodeCredentials.getExpiresIn());


		} catch(IOException | SpotifyWebApiException | ParseException e) {

			System.out.println("Error " + e.getMessage());
		}
	}

	public static void authCodeRefreshAsync(){

		try{
			final CompletableFuture<AuthorizationCodeCredentials> authorizationCodeCredentialsFuture = authorizationCodeRefreshRequest.executeAsync();

			final AuthorizationCodeCredentials authorizationCodeCredentials = authorizationCodeCredentialsFuture.join();

			spotifyApi.setAccessToken(authorizationCodeCredentials.getAccessToken());

			System.out.println("Expires in ..." + authorizationCodeCredentials.getExpiresIn());

		} catch (CompletionException e) {

			System.out.println("Error " + e.getCause().getMessage());
		} catch (CancellationException e) {

			System.out.println("Async operation cancelled")
		}
	}



	public static void main(String[] args) {

		SpringApplication.run(LyricsAnalyserApplication.class, args);
	}


}
