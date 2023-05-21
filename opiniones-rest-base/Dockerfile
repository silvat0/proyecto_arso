FROM mcr.microsoft.com/dotnet/sdk:6.0
COPY . .
WORKDIR ./opiniones-rest
RUN dotnet dev-certs https --trust
CMD ["dotnet", "run"] 
EXPOSE 5110
EXPOSE 7271