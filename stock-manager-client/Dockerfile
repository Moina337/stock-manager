FROM node:20-bullseye AS build
WORKDIR /app

ENV NODE_OPTIONS="--max-old-space-size=2048"

COPY package*.json ./
RUN npm ci
COPY . .

RUN npm run build -- --configuration=production

FROM nginx:alpine
COPY --from=build /app/dist/gestion-produit-crud/browser /usr/share/nginx/html
RUN echo "server { listen 80; location / { root /usr/share/nginx/html; index index.html; try_files \$uri \$uri/ /index.html; } }" > /etc/nginx/conf.d/default.conf
EXPOSE 80
CMD ["nginx", "-g", "daemon off;"]
