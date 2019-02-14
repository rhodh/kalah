package com.rhod.kalah.services;

import com.rhod.kalah.services.game.GameServer;
import com.rhod.kalah.services.logic.LogicServer;
import com.rhod.kalah.services.registration.RegistrationServer;
import com.rhod.kalah.services.repository.RepositoryServer;

public class Main {

	public static void main(String[] args) {

		String serverName = "NO-VALUE";

		switch (args.length) {
		case 2:
			// Optionally set the HTTP port to listen on, overrides
			// value in the <server-name>-server.yml file
			System.setProperty("server.port", args[1]);
			// Fall through into ..

		case 1:
			serverName = args[0].toLowerCase();
			break;

		default:
			usage();
			return;
		}
		
		//TODO could put a factory here
		if (serverName.equals("registration")) {
			RegistrationServer.main(args);
		} else if (serverName.equals("logic")) {
			LogicServer.main(args);
		} else if(serverName.equals("repository")) {
			RepositoryServer.main(args);
		} else if(serverName.equals("game")) {
			GameServer.main(args);
		} else {
			System.out.println("Unknown server type: " + serverName);
			usage();
		}
	}

	protected static void usage() {
		System.out.println("Usage: java -jar ... <server-name> [server-port]");
		System.out.println(
				"     where server-name is 'registration', 'logic' and server-port > 1024");
	}
}
