IMAGE_NAME=api-gateway
CONTAINER_NAME=api-gateway
PORT=4004

build:
	docker build -t $(IMAGE_NAME) .

run:
	docker rm -f $(CONTAINER_NAME)
	docker run --rm -d -p $(PORT):$(PORT) --name $(CONTAINER_NAME) --network internal $(IMAGE_NAME)

stop:
	docker stop $(CONTAINER_NAME)
	docker rm $(CONTAINER_NAME)

logs:
	docker logs -f $(CONTAINER_NAME)