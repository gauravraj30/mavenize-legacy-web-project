node {
	scmUrl = null
	gitCredentialId = 'pietroaragonaGitHub' // gitRtmCredential


	stage ('Preparation'){
		echo "Preparation in progress..."
		scmUrl = scm.getUserRemoteConfigs()[0].getUrl()
		echo "clone from ${scmUrl}, branch: ${env.BRANCH_NAME}"

		git credentialsId: '${gitCredentialId}', url: "${scmUrl}", branch: "${env.BRANCH_NAME}"
	}

	stage ('Build'){
		if(isUnix()){
			sh "mvn clean package -DskipTests"
		}
		else{
			bat "mvn clean package -DskipTests"
		}

	}

	stage ('Deploy to remote repository'){
		repoPush = null
		remoteRepoHost='artifactory-host'
		remoteRepoPort=8081

		if ("${env.BRANCH_NAME}" == 'master'){
			repoPush='libs-release'
		}else if ("${env.BRANCH_NAME}" == 'develop'){	
			repoPush='libs-snapshot'
		}


		sh "mvn -B clean deploy -DaltDeploymentRepository=snapshots::default::http://${remoteRepoHost}:${remoteRepoPort}/artifactory/${repoPush} -Dbuild.number='${env.BUILD_NUMBER}' -DskipTests  -Djava.Awt.Headless=true -Djsse.enableSNIExtension=false "



	}
}
