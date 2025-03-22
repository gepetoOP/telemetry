Variáveis de ambiente:

- OTEL_EXPORTER_OTLP_ENDPOINT=http://localhost:4317;OTEL_EXPORTER_OTLP_PROTOCOL=grpc;OTEL_SERVICE_NAME=telemetry;OTEL_TRACES_EXPORTER=otlp
- Com agent: JAVA_TOOL_OPTIONS=-javaagent:opentelemetry-javaagent.jar

Como rodar o projeto:
- Local:
  - docker-compose -f (sobe todos os containers)
  - ./mvnw clean install (roda a aplicação)
- PRD:
  - docker-compose -f docker-compose-prd.yml up --build

- Jaegger (http://localhost:16686/)
- Grafana (http://localhost:3000/ user=admin, pass=admin)
- Prometheus (http://localhost:9090/)