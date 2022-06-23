if [ $# -ne 1 ]; then 
	echo "Environment argument missing (dev, prod)"
	exit
fi

if [ $1 != "prod" ] && [ $1 != "dev" ]; then
	echo "Invalid option $1. Possible values: dev, prod"
	exit
fi

docker run -p8080:8080 -e "SPRING_PROFILES_ACTIVE=$1" --name hbp_app hbp
docker rm hbp_app
